package healthyLife.contractWrappers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import healthyLife.serverApi.wrappers.AccountControllerApi;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint8;
import healthyLife.contractWrappers.base.RawContract;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.exceptions.ContractCallException;


public class AccountController extends RawContract implements AccountControllerApi {

    public static final String FUNC_GETUSERCLASS = "getUserClass";

    public static final String FUNC_GETUSERCONTRACTADDRESS = "getUserContractAddress";

    public static final String FUNC_ISREGISTERED = "isRegistered";

    public static final String FUNC_REGISTRYDOCTOR = "registryDoctor";

    public static final String FUNC_REGISTRYPATIENT = "registryPatient";

    public static final String FUNC_REGISTRYPROVIDER = "registryProvider";

    public static final String FUNC_SETACCOUNTSTORAGE = "setAccountStorage";

    public static final String FUNC_SETUSERCONTRACTSFACTORY = "setUserContractsFactory";

    public static final String FUNC_TRANSACTIONALLOWED = "transactionAllowed";



    protected AccountController(String contractAddress, String senderAddress, Web3j web3j){
        super(contractAddress, senderAddress, web3j);
    }

//    /**
//     * Метод создает обертку для контракта, размещенного по указанному адресу.
//     * Метод не проверяет действительно ли по указанному адресу находится необходимый контракт.
//     * Он делает проверку, что по указанному адресу хранится какой-либо код
//     * @param contractAddress адрес загружаемого контракта
//     * @param senderAddress адрес отправителя, который будет подставляться при вызове eth_call (чтение данных из блокчейна)
//     * @param web3j Web3j instance
//     * @return AccountController
//     */
    public static AccountController load(String contractAddress, String senderAddress,  Web3j web3j){
        if(!hasCode(contractAddress, web3j)){
            throw new ContractCallException("There is no smart contract at the specified address");
        }
        return new AccountController(contractAddress, senderAddress, web3j);
    }
    
//    /**
//     * Метод возвращает RemoteFunctionCall, вызов которого вернет адрес контракта UserContract.
//     * В случае если указанный в параметрах аккаунт является пациентом, по полученному адресу можно загрузить PatientContract
//     * В случае если указанный в параметрах аккаунт является врачом, по полученному адресу можно загрузить DoctorContract
//     * если аккаунт не зарегистрирован, то будет вызвана ошибка ContractCallException
//     * @param account Ethereum адрес пользователя, который зарегистрирован в системе
//     * @return RemoteFunctionCall, который вернет String - адрес контракта UserContract
//     */
    public RemoteFunctionCall<String> getUserContractAddress(String account) {
        final Function function = new Function(FUNC_GETUSERCONTRACTADDRESS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

//    /**
//     * Метод возвращает RemoteFunctionCall, вызов которого вернет класс пользователя _address типа BigInteger (UserClass).
//     * если аккаунт не зарегистрирован, то будет вызвана ошибка ContractCallException
//     * @param _address Ethereum адрес пользователя, который зарегистрирован в системе
//     * @return RemoteFunctionCall, который вернет BigInteger - класс пользователя (можно конвертировать в UserClass)
//     * @see UserClass
//     */
    public RemoteFunctionCall<BigInteger> getUserClass(String _address) {
        final Function function = new Function(FUNC_GETUSERCLASS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }


//    /**
//     * Метод возвращает RemoteFunctionCall, вызов которого возвращает результат проверки, зарегистрирован ли указанный аккаунт в системе
//     * @param account Ethereum address
//     * @return RemoteFunctionCall, вызов которого вернет результат Boolean
//     */
    public RemoteFunctionCall<Boolean> isRegistered(String account) {
        final Function function = new Function(FUNC_ISREGISTERED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }



//    /** Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
//     * Транзакция позволит зарегистрировать пользователя с адресом account в системе как врача
//     * Если аккаунт уже зарегистрирован, то при обработке транзакции будет вызвана ошибка TransactionException
//     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
//     * @param account адрес регистрируемого пользователя
//     * @return RawTransaction транзакция для подписания приватным ключом senderAddress
//     * @see AccountController#executeRegistryDoctor(String)
//     */
    public RawTransaction registryDoctorRawTransaction(String account) {
        final Function function = new Function(
                FUNC_REGISTRYDOCTOR,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }

//    /**
//     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе registryDoctorRawTransaction.
//     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
//     * метод завершится с ошибкой TransactionException.
//     * Если аккаунт уже зарегистрирован, то при обработке транзакции будет вызвана ошибка TransactionException
//     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет
//     * @param hexTransaction подписанная RawTransaction
//     * @return RemoteCall, вызов которого вернет TransactionReceipt
//     * @see AccountController#registryDoctorRawTransaction(String)
//     */
    public RemoteCall<TransactionReceipt> executeRegistryDoctor(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_REGISTRYDOCTOR,
                Arrays.<Type>asList(Address.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }

//    /**
//     * Метод возвращает RawTransaction, которую необходимо подписать отправителю
//     * Транзакция позволит зарегистрировать пользователя с адресом account в системе как пациента
//     * Если аккаунт уже зарегистрирован, то при обработке транзакции будет вызвана ошибка TransactionException
//     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
//     * @param account
//     * @return RawTransaction, которую должен подписать senderAddress
//     * @see AccountController#executeRegistryPatient(String)
//     */
    public RawTransaction registryPatientRawTransaction(String account) {
        final Function function = new Function(
                FUNC_REGISTRYPATIENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }

//    /**
//     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе registryPatientRawTransaction.
//     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
//     * метод завершится с ошибкой TransactionException.
//     * Если аккаунт уже зарегистрирован, то при обработке транзакции будет вызвана ошибка TransactionException
//     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет/
//     * @param hexTransaction подписанная RawTransaction
//     * @see AccountController#registryPatientRawTransaction(String)
//     */
    public RemoteCall<TransactionReceipt> executeRegistryPatient(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_REGISTRYPATIENT,
                Arrays.<Type>asList(Address.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }

//    /**
//     *
//     * Метод возвращает RawTransaction, которую необходимо подписать отправителю.
//     * <p>
//     * Транзакция позволит зарегистрировать пользователя с адресом account в системе как провайдера.
//     * Регистрируемый адрес account должен являться администратором.
//     *
//     * Если аккаунт уже зарегистрирован и не является администратором, то при обработке транзакции будет вызвана ошибка TransactionException
//     * <p>
//     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
//     * @param account регистрируемый аккаунт
//     * @return RawTransaction, которую должен подписать senderAddress
//     * @see AccountController#executeRegistryProvider(String)
//     */
    public RawTransaction registryProviderRawTransaction(String account) {
        final Function function = new Function(
                FUNC_REGISTRYPROVIDER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }

//    /**
//     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе registryProviderRawTransaction.
//     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
//     * метод завершится с ошибкой TransactionException.
//     * Если аккаунт уже зарегистрирован или не является администратором, то при обработке транзакции будет вызвана ошибка TransactionException.
//     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет/
//     * @param hexTransaction подписанная RawTransaction
//     * @return RemoteCall, вызов которого вернет TransactionReceipt
//     * @see AccountController#registryProviderRawTransaction(String)
//     */
    public RemoteCall<TransactionReceipt> executeRegistryProvider(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_REGISTRYPROVIDER,
                Arrays.<Type>asList(Address.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }
}
