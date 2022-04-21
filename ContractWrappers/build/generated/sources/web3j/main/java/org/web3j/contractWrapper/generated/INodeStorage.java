package org.web3j.contractWrapper.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.0.
 */
@SuppressWarnings("rawtypes")
public class INodeStorage extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_IDONLYMODE = "IdOnlyMode";

    public static final String FUNC_ADD = "add";

    public static final String FUNC_calculateKey = "calculateKey";

    public static final String FUNC_exists = "exists";

    public static final String FUNC_GETBYINDEX = "getByIndex";

    public static final String FUNC_GETENODEIDNUM = "getEnodeIdNum";

    public static final String FUNC_GETINDEXOF = "getIndexOf";

    public static final String FUNC_REMOVE = "remove";

    public static final String FUNC_SETIDONLYMODE = "setIdOnlyMode";

    public static final String FUNC_SIZE = "size";

    @Deprecated
    protected INodeStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected INodeStorage(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected INodeStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected INodeStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Boolean> IdOnlyMode() {
        final Function function = new Function(FUNC_IDONLYMODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> add(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> calculateKey(Enode enode) {
        final Function function = new Function(FUNC_calculateKey, 
                Arrays.<Type>asList(enode), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> calculateKey(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(FUNC_calculateKey, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> exists(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(FUNC_exists, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> exists(BigInteger enodeKey) {
        final Function function = new Function(FUNC_exists, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(enodeKey)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple3<String, String, BigInteger>> getByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint16>() {}));
        return new RemoteFunctionCall<Tuple3<String, String, BigInteger>>(function,
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getEnodeIdNum(String enodeId) {
        final Function function = new Function(FUNC_GETENODEIDNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getIndexOf(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(FUNC_GETINDEXOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> remove(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(
                FUNC_REMOVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setIdOnlyMode(Boolean value) {
        final Function function = new Function(
                FUNC_SETIDONLYMODE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> size() {
        final Function function = new Function(FUNC_SIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static INodeStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new INodeStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static INodeStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new INodeStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static INodeStorage load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new INodeStorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static INodeStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new INodeStorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<INodeStorage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(INodeStorage.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<INodeStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(INodeStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<INodeStorage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(INodeStorage.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<INodeStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(INodeStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Enode extends DynamicStruct {
        public String enodeId;

        public String enodeHost;

        public BigInteger enodePort;

        public Enode(String enodeId, String enodeHost, BigInteger enodePort) {
            super(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                    new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                    new org.web3j.abi.datatypes.generated.Uint16(enodePort));
            this.enodeId = enodeId;
            this.enodeHost = enodeHost;
            this.enodePort = enodePort;
        }

        public Enode(Utf8String enodeId, Utf8String enodeHost, Uint16 enodePort) {
            super(enodeId, enodeHost, enodePort);
            this.enodeId = enodeId.getValue();
            this.enodeHost = enodeHost.getValue();
            this.enodePort = enodePort.getValue();
        }
    }
}
