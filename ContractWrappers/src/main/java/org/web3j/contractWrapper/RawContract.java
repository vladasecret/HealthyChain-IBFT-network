package org.web3j.contractWrapper;

import org.web3j.abi.*;
import org.web3j.abi.datatypes.*;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionDecoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class RawContract {
    public static final int DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH = 20;
    public static final long DEFAULT_POLLING_FREQUENCY = 1000;

    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(29999971);
    public static final BigInteger GAS_PRICE= BigInteger.ZERO;

    protected final String contractBinary;
    protected String contractAddress;
    protected String senderAddress;
    protected static DefaultBlockParameter defaultBlockParameter = DefaultBlockParameterName.PENDING;
    protected Web3j web3j;

    protected TransactionReceiptProcessor transactionReceiptProcessor;

    protected RawContract(String contractBinary, String contractAddress, String senderAddress, Web3j web3j){
        if (!(WalletUtils.isValidAddress(contractAddress) && WalletUtils.isValidAddress(senderAddress))){
            throw new IllegalArgumentException("Not valid address");
        }
        this.contractBinary = contractBinary;
        this.contractAddress = contractAddress;
        this.senderAddress = senderAddress;
        this.web3j = web3j;
        transactionReceiptProcessor = new PollingTransactionReceiptProcessor(web3j, DEFAULT_POLLING_FREQUENCY, DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH);
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public String getSenderAddress(){
        return senderAddress;
    }

    public void setSenderAddress(String newAddress){
        if (!WalletUtils.isValidAddress(newAddress))
            throw new IllegalArgumentException("Not valid address: " + newAddress);
        senderAddress = newAddress;

    }

    protected static RawTransaction createContractTransaction(String fromAddress, Web3j web3j, String contractBinary) {
        RawTransaction transaction = RawTransaction
                .createContractTransaction(getNonce(fromAddress, web3j), GAS_PRICE, GAS_LIMIT, BigInteger.ZERO, contractBinary);
        return transaction;
    }

    protected static BigInteger getNonce(String address, Web3j web3j) {
        EthGetTransactionCount ethGetTransactionCount = null;
        try {
            ethGetTransactionCount = web3j.ethGetTransactionCount(
                    address, defaultBlockParameter).sendAsync().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return BigInteger.ZERO;
        }
        return ethGetTransactionCount.getTransactionCount();
    }

    protected RemoteCall<TransactionReceipt> executeRemoteCallSignedTransaction(String hexTransaction, Function defaultFunction) throws TransactionException {
        RawTransaction rawTransaction = TransactionDecoder.decode(hexTransaction);
        if (!rawTransaction.getTo().equals(contractAddress))
            throw new TransactionException("Recipient's address does not match the contract address");
        if (!rawTransaction.getData().substring(0, 8).equals(FunctionEncoder.encode(defaultFunction).substring(2, 10)))
            throw new TransactionException("Function methodID does not match in transaction");
        return executeRemoteCallSignedTransaction(hexTransaction, web3j, transactionReceiptProcessor);
    }

    protected static RemoteCall<TransactionReceipt> executeRemoteCallSignedTransaction(String hexTransaction, Web3j web3j) {
        return executeRemoteCallSignedTransaction(hexTransaction, web3j, new PollingTransactionReceiptProcessor(web3j, DEFAULT_POLLING_FREQUENCY,
                DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH));
    }

    protected static RemoteCall<TransactionReceipt> executeRemoteCallSignedTransaction(String hexTransaction, Web3j web3j,
                                                                                       TransactionReceiptProcessor transactionReceiptProcessor) {
        return new RemoteCall<>(() -> executeSignedTransaction(hexTransaction, web3j, transactionReceiptProcessor));

    }

    private static TransactionReceipt executeSignedTransaction(String hexTransaction, Web3j web3j,
                                                               TransactionReceiptProcessor transactionReceiptProcessor) throws IOException, TransactionException {
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexTransaction).send();
        if (ethSendTransaction.hasError()) {
            throw new TransactionException("Error processing transaction request: "
                    + ethSendTransaction.getError().getMessage() + ethSendTransaction.getError().getData(), ethSendTransaction.getTransactionHash());
        }
        return transactionReceiptProcessor.waitForTransactionReceipt(ethSendTransaction.getTransactionHash());
    }

    protected static <T extends RawContract> RemoteCall<T>  deployRemoteCall(Class<T> type, String binary, String hexTransaction, Web3j web3j){
        return new RemoteCall<>(() -> deploy(type, binary, hexTransaction, web3j));
    }

    protected static <T extends RawContract> T deploy(Class<T> type, String binary, String hexTransaction, Web3j web3j) throws TransactionException, ExecutionException, InterruptedException {
        RawTransaction rawTransaction = TransactionDecoder.decode(hexTransaction);
        if (!(rawTransaction.getData().startsWith(binary) && rawTransaction.getTo().equals("0x"))){
            throw new TransactionException("transaction does not match deploy function");
        }

        try {
            TransactionReceipt transactionReceipt = executeRemoteCallSignedTransaction(hexTransaction, web3j).sendAsync().get();
            if (!transactionReceipt.isStatusOK())
                throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
            Constructor<T> constructor =
                    type.getDeclaredConstructor(
                            String.class,
                            String.class,
                            Web3j.class);
            constructor.setAccessible(true);

            // we want to use null here to ensure that "to" parameter on message is not populated
            T contract = constructor.newInstance(transactionReceipt.getContractAddress(), transactionReceipt.getFrom(), web3j);
            return contract;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends Type, R> R executeCallSingleValueReturn(
            Function function, Class<R> returnType) throws IOException {
        T result = executeCallSingleValueReturn(function);
        if (result == null) {
            throw new ContractCallException("Empty value (0x) returned from contract");
        }

        Object value = result.getValue();
        if (returnType.isAssignableFrom(result.getClass())) {
            return (R) result;
        } else if (returnType.isAssignableFrom(value.getClass())) {
            return (R) value;
        } else if (result.getClass().equals(Address.class) && returnType.equals(String.class)) {
            return (R) result.toString(); // cast isn't necessary
        } else {
            throw new ContractCallException(
                    "Unable to convert response: "
                            + value
                            + " to expected type: "
                            + returnType.getSimpleName());
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends Type> T executeCallSingleValueReturn(Function function)
            throws IOException {
        List<Type> values = executeCall(function);
        if (!values.isEmpty()) {
            return (T) values.get(0);
        } else {
            return null;
        }
    }

    private List<Type> executeCall(Function function) throws IOException {
        Transaction transaction = Transaction
                .createEthCallTransaction(senderAddress, contractAddress, FunctionEncoder.encode(function));
        EthCall response = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
        if (response.isReverted())
            throw new ContractCallException(response.getRevertReason());
        return FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
    }

    protected List<Type> executeCallMultipleValueReturn(Function function) throws IOException {
        return executeCall(function);
    }

    protected RemoteFunctionCall<List<Type>> executeRemoteCallMultipleValueReturn(
            Function function) {
        return new RemoteFunctionCall<>(function, () -> executeCallMultipleValueReturn(function));
    }

    protected <T> RemoteFunctionCall<T> executeRemoteCallSingleValueReturn(
            Function function, Class<T> returnType) {
        return new RemoteFunctionCall<>(
                function, () -> executeCallSingleValueReturn(function, returnType));
    }

    protected List<RawContract.EventValuesWithLog> extractEventParametersWithLog(
            Event event, TransactionReceipt transactionReceipt) {
        return transactionReceipt.getLogs().stream()
                .map(log -> extractEventParametersWithLog(event, log))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    protected RawContract.EventValuesWithLog extractEventParametersWithLog(Event event, Log log) {
        return staticExtractEventParametersWithLog(event, log);
    }

    protected static RawContract.EventValuesWithLog staticExtractEventParametersWithLog(Event event, Log log) {
        final EventValues eventValues = staticExtractEventParameters(event, log);
        return (eventValues == null) ? null : new RawContract.EventValuesWithLog(eventValues, log);
    }

    public static EventValues staticExtractEventParameters(Event event, Log log) {
        final List<String> topics = log.getTopics();
        String encodedEventSignature = EventEncoder.encode(event);
        if (topics == null || topics.size() == 0 || !topics.get(0).equals(encodedEventSignature)) {
            return null;
        }

        List<Type> indexedValues = new ArrayList<>();
        List<Type> nonIndexedValues =
                FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters());

        List<TypeReference<Type>> indexedParameters = event.getIndexedParameters();
        for (int i = 0; i < indexedParameters.size(); i++) {
            Type value =
                    FunctionReturnDecoder.decodeIndexedValue(
                            topics.get(i + 1), indexedParameters.get(i));
            indexedValues.add(value);
        }
        return new EventValues(indexedValues, nonIndexedValues);
    }

    protected RawTransaction createRawTransaction(Function function) {
        return RawTransaction.createTransaction(getNonce(senderAddress, web3j), GAS_PRICE, GAS_LIMIT, contractAddress,
                FunctionEncoder.encode(function));
    }

//    protected static RawTransaction createRawTransaction(String senderAddress, Web3j web3j, String data) {
//        return RawTransaction.createTransaction(getNonce(senderAddress, web3j), GAS_LIMIT, GAS_PRICE, contractAddress,
//                FunctionEncoder.encode(function));
//    }


    public static boolean hasCode(String contractAddress, Web3j web3j){
        try {

            EthGetCode ethGetCode = web3j
                    .ethGetCode(contractAddress, DefaultBlockParameter.valueOf(DefaultBlockParameterName.LATEST.name()))
                    .send();
            if (ethGetCode.hasError()){
                throw new ContractCallException(ethGetCode.getError().getMessage());
            }

            String code = Numeric.cleanHexPrefix(ethGetCode.getCode());

            return !code.isEmpty();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static class EventValuesWithLog {
        private final EventValues eventValues;
        private final Log log;

        private EventValuesWithLog(EventValues eventValues, Log log) {
            this.eventValues = eventValues;
            this.log = log;
        }

        public List<Type> getIndexedValues() {
            return eventValues.getIndexedValues();
        }

        public List<Type> getNonIndexedValues() {
            return eventValues.getNonIndexedValues();
        }

        public Log getLog() {
            return log;
        }
    }

    @SuppressWarnings("unchecked")
    protected static <S extends Type, T> List<T> convertToNative(List<S> arr) {
        List<T> out = new ArrayList<>();
        for (final S s : arr) {
            if (StructType.class.isAssignableFrom(s.getClass())) {
                out.add((T) s);
            } else {
                out.add((T) s.getValue());
            }
        }
        return out;
    }
}
