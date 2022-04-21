package org.web3j.contractWrapper;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.exceptions.ContractCallException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class RegistryContract extends RawContract{
    public static String BINARY = org.web3j.contractWrapper.generated.RegistryContract.BINARY;

    public static final String FUNC_ACCOUNT_CONTROLLER_CONTRACT = "ACCOUNT_CONTROLLER_CONTRACT";

    public static final String FUNC_ADMIN_CONTRACT = "ADMIN_CONTRACT";

    public static final String FUNC_NODE_CONTROLLER_CONTRACT = "NODE_CONTROLLER_CONTRACT";

    public static final String FUNC_GETALLCONTRACTKEYS = "getAllContractKeys";

    public static final String FUNC_GETCONTRACTADDRESS = "getContractAddress";

    public static final String FUNC_GETSIZE = "getSize";

    public static final String FUNC_HASCONTRACTADDRESS = "hasContractAddress";

    public static final String FUNC_ISADMIN = "isAdmin";

    public static final String FUNC_ISAUTHORIZED = "isAuthorized";

    public static final String FUNC_REMOVECONTRACT = "removeContract";

    public static final String FUNC_SETCONTRACTADDRESS = "setContractAddress";

    public static final Event REGISTRYUPDATED_EVENT = new Event("RegistryUpdated",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
    ;


    protected RegistryContract(String contractAddress, String senderAddress, Web3j web3j){
        super(BINARY, contractAddress, senderAddress,  web3j);
    }

    public static RawTransaction createContractRawTransaction(String fromAddress, Web3j web3j)  {
        return RawTransaction
                .createContractTransaction(getNonce(fromAddress, web3j), GAS_PRICE, GAS_LIMIT, BigInteger.ZERO, BINARY);
    }

    /**
     * Метод возвращает объект RemoteCall, вызов которого развернет новый контракт в сети, установленной параметром web3j,
     * и в случае успеха вернет новый объект RegistryContract
     *  <p>
     * Параметр hexTransaction должен быть подписанной RawTransaction, полученной в методе createContractRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * вызов RemoteCall завершится с ошибкой TransactionException
     *
     * @param hexTransaction подписанная RawTransaction полученная в методе createContractRawTransaction
     * @param web3j
     * @return RemoteCall<RegistryContract>, вызов которого развернет новый RegistryContract в сети и вернет новый объект RegistryContract
     *
     * @see RegistryContract#createContractRawTransaction(String, Web3j) 
     */
    public static RemoteCall<RegistryContract> deploy(String hexTransaction, Web3j web3j) {
        return deployRemoteCall(RegistryContract.class, BINARY, hexTransaction, web3j);
    }

    /**
     * Метод создает обертку для контракта, размещенного по указанному адресу.
     * Метод не проверяет действительно ли по указанному адресу находится необходимый контракт.
     * Он делает проверку, что по указанному адресу хранится какой-либо код
     * @param contractAddress адрес загружаемого контракта
     * @param senderAddress адрес отправителя, который будет подставляться при вызове eth_call (чтение данных из блокчейна)
     * @param web3j
     * @return RegistryContract
     */
    public static RegistryContract load(String contractAddress, String senderAddress,  Web3j web3j){
        if(!hasCode(contractAddress, web3j)){
            throw new ContractCallException("There is no smart contract at the specified address");
        }
        return new RegistryContract(contractAddress, senderAddress, web3j);
    }

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall<byte[]>, вызов которого вернет значение константы ACCOUNT_CONTROLLER_CONTRACT.
     * ACCOUNT_CONTROLLER_CONTRACT - имя контракта AccountController, хранящееся в контракте как bytes32
     *
     * @return RemoteFunctionCall<byte[]>, вызов которого вернет значение константы ACCOUNT_CONTROLLER_CONTRACT
     */
    public RemoteFunctionCall<byte[]> ACCOUNT_CONTROLLER_CONTRACT() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ACCOUNT_CONTROLLER_CONTRACT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall<byte[]>, вызов которого вернет значение константы ADMIN_CONTRACT.
     * ADMIN_CONTRACT - имя для контракта Admin, хранящееся как bytes32
     *
     * @return RemoteFunctionCall<byte[]>, вызов которого вернет значение константы ADMIN_CONTRACT
     */
    public RemoteFunctionCall<byte[]> ADMIN_CONTRACT() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ADMIN_CONTRACT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall<byte[]>, вызов которого вернет значение константы NODE_CONTROLLER_CONTRACT.
     * NODE_CONTROLLER_CONTRACT - имя для контракта NodeController, хранящееся как bytes32
     *
     * @return RemoteFunctionCall<byte[]>, вызов которого вернет значение константы NODE_CONTROLLER_CONTRACT
     */
    public RemoteFunctionCall<byte[]> NODE_CONTROLLER_CONTRACT() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NODE_CONTROLLER_CONTRACT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    /**
     * Метод(eth_call) вернет RemoteFunctionCall<List>, вызов которого вернет List<byte[]> элементами которого будут имена всех записанных в
     * RegistryContract контрактов
     * <p>
     * Метод выполнит вызов от адреса, указанного как senderAddress в конструкторе
     * @return RemoteFunctionCall<List>
     */
    public RemoteFunctionCall<List> getAllContractKeys() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETALLCONTRACTKEYS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall<String>, вызов которого вернет адрес контракта по его имени.
     * <p>
     * Если для указанного имени не зарегистрирован контракт, то будет возвращено значение Address.DEFAULT ("0x0000000000000000000000000000000000000000")
     * @param name имя контракта в виде byte[32]
     * @return RemoteFunctionCall<String>, который вернет адрес контракта
     */
    public RemoteFunctionCall<String> getContractAddress(byte[] name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCONTRACTADDRESS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(name)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }


    /**
     * Метод(eth_call) возвращает RemoteFunctionCall<BigInteger>, вызов которого вернет количество зарегистрированных контрактов
     * @return RemoteFunctionCall<BigInteger>
     */
    public RemoteFunctionCall<BigInteger> getSize() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSIZE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall<Boolean>, вызов которого вернет результат проверки, зарегестрирован ли контракт по укказанному имени
     * @param name имя контракта в виду byte[32]
     * @return RemoteFunctionCall<BigInteger>
     */
    public RemoteFunctionCall<Boolean> hasContractAddress(byte[] name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HASCONTRACTADDRESS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(name)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall<Boolean>, вызов которого вернет результат проверки является ли указанный адрес администратором.
     * В случае, если в контракте по имени ADMIN_CONTRACT не зарегистрирован контракт, то метод будет возвращать true
     * @param account Ethereum адрес пользователя
     * @return RemoteFunctionCall<Boolean>
     */
    public RemoteFunctionCall<Boolean> isAdmin(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISADMIN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall<Boolean>, вызов которого вернет результат проверки является ли указанный адрес администратором
     * или зарегистрирован как пользователь.
     * В случае, если по именам ADMIN_CONTRACT и ACCOUNT_CONTROLLER_CONTRACT не зарегистрированы контракты, то метод вернет true
     * @param account Ethereum адрес пользователя
     * @return RemoteFunctionCall<Boolean>
     */
    public RemoteFunctionCall<Boolean> isAuthorized(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAUTHORIZED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю.
     * Транзакция позволит вызвать метод контракта removeContract.
     * Метод контракта removeContract удалит по указанному имени зарегистрированный адрес контракта
     * Метод подставит значение nonce корректное для senderAddress
     * @param _name имя контракта в виде byte[32]
     * @return RawTransaction, которую необходимо подписать
     * @throws ExecutionException
     * @throws InterruptedException
     * @see RegistryContract#executeRemoveContract(String)
     */
    public RawTransaction removeContractRawTransaction(byte[] _name) throws ExecutionException, InterruptedException {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVECONTRACT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_name)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
//        return RawTransaction.createTransaction(getNonce(senderAddress, web3j), GAS_LIMIT, GAS_PRICE, contractAddress,
//                FunctionEncoder.encode(function));
    }

    /**
     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе removeContractRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * вызов RemoteCall завершится с ошибкой TransactionException
     * Метод подставит значение nonce корректное для senderAddress
     * <p>
     * Чтобы транзакция выполнилась успешно, подписывающий должен быть создателем контракта. В противном случае транзакция выполнена не будет
     * @param hexTransaction транзакция, полученная в методе removeContractRawTransaction, подписанная создателем контракта
     * @return RemoteCall<TransactionReceipt> TransactionReceipt хранит информацию о выполнении транзакции
     * @throws TransactionException
     * @see RegistryContract#removeContractRawTransaction(byte[])
     */
    public RemoteCall<TransactionReceipt> executeRemoveContract(String hexTransaction) throws TransactionException {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVECONTRACT,
                Arrays.<Type>asList(Bytes32.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }

    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как sederAddress.
     * Транзакция позволит зарегистрировать адрес контракта addr по указанному имени name
     * @param name имя регистрируемого контракта byte[32]
     * @param addr адрес регистрируемого контракта
     * @return RawTransaction, которую необходимо подписать
     * @see RegistryContract#executeSetContractAddress(String)
     */
    public RawTransaction setContractAddressRawTransaction(byte[] name, String addr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETCONTRACTADDRESS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(name),
                        new org.web3j.abi.datatypes.Address(160, addr)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }


    /**
     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе setContractAddressRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * вызов RemoteCall завершится с ошибкой TransactionException
     * <p>
     * Чтобы транзакция выполнилась успешно, подписывающий должен быть создателем контракта. В противном случае транзакция выполнена не будет
     * @param hexTransaction транзакция, полученная в методе setContractAddressRawTransaction, подписанная создателем контракта
     * @return RemoteCall<TransactionReceipt> TransactionReceipt хранит информацию о выполнении транзакции
     * @throws TransactionException
     * @see RegistryContract#setContractAddressRawTransaction(byte[], String)
     */
    public RemoteCall<TransactionReceipt> executeSetContractAddress(String hexTransaction) throws TransactionException {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETCONTRACTADDRESS,
                Arrays.<Type>asList(Bytes32.DEFAULT,
                        Address.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }




}
