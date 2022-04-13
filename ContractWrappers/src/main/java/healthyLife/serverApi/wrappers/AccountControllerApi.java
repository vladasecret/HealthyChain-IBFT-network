package healthyLife.serverApi.wrappers;

import healthyLife.contractWrappers.AccountController;
import healthyLife.serverApi.wrappers.base.RawContractApi;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * @see healthyLife.contractWrappers.AccountController
 */
public interface AccountControllerApi extends RawContractApi {


    public enum UserClass {PATIENT, DOCTOR, PROVIDER, DELETED}

    /**
     * Метод возвращает RemoteFunctionCall, вызов которого вернет адрес контракта UserContract.
     * В случае если указанный в параметрах аккаунт является пациентом, по полученному адресу можно загрузить PatientContract
     * В случае если указанный в параметрах аккаунт является врачом, по полученному адресу можно загрузить DoctorContract
     * если аккаунт не зарегистрирован, то будет вызвана ошибка ContractCallException
     * @param account Ethereum адрес пользователя, который зарегистрирован в системе
     * @return RemoteFunctionCall, который вернет String - адрес контракта UserContract
     */
    public RemoteFunctionCall<String> getUserContractAddress(String account);

    /**
     * Метод возвращает RemoteFunctionCall, вызов которого вернет класс пользователя _address типа BigInteger (UserClass).
     * если аккаунт не зарегистрирован, то будет вызвана ошибка ContractCallException
     * @param _address Ethereum адрес пользователя, который зарегистрирован в системе
     * @return RemoteFunctionCall, который вернет BigInteger - класс пользователя (можно конвертировать в UserClass)
     * @see AccountController.UserClass
     */
    public RemoteFunctionCall<BigInteger> getUserClass(String _address);

    /**
     * Метод возвращает RemoteFunctionCall, вызов которого возвращает результат проверки, зарегистрирован ли указанный аккаунт в системе
     * @param account Ethereum address
     * @return RemoteFunctionCall, вызов которого вернет результат Boolean
     */
    public RemoteFunctionCall<Boolean> isRegistered(String account);


    /** Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * Транзакция позволит зарегистрировать пользователя с адресом account в системе как врача
     * Если аккаунт уже зарегистрирован, то при обработке транзакции будет вызвана ошибка TransactionException
     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
     * @param account адрес регистрируемого пользователя
     * @return RawTransaction транзакция для подписания приватным ключом senderAddress
     * @see AccountControllerApi#executeRegistryDoctor(String)
     */
    public RawTransaction registryDoctorRawTransaction(String account);

    /**
     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе registryDoctorRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * метод завершится с ошибкой TransactionException.
     * Если аккаунт уже зарегистрирован, то при обработке транзакции будет вызвана ошибка TransactionException
     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет
     * @param hexTransaction подписанная RawTransaction
     * @return RemoteCall, вызов которого вернет TransactionReceipt
     * @see AccountControllerApi#registryDoctorRawTransaction(String)
     */
    public RemoteCall<TransactionReceipt> executeRegistryDoctor(String hexTransaction) throws TransactionException;


    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю
     * Транзакция позволит зарегистрировать пользователя с адресом account в системе как пациента
     * Если аккаунт уже зарегистрирован, то при обработке транзакции будет вызвана ошибка TransactionException
     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
     * @param account
     * @return RawTransaction, которую должен подписать senderAddress
     * @see AccountControllerApi#executeRegistryPatient(String)
     */
    public RawTransaction registryPatientRawTransaction(String account);

    /**
     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе registryPatientRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * метод завершится с ошибкой TransactionException.
     * Если аккаунт уже зарегистрирован, то при обработке транзакции будет вызвана ошибка TransactionException
     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет/
     * @param hexTransaction подписанная RawTransaction
     * @see AccountControllerApi#registryPatientRawTransaction(String)
     */
    public RemoteCall<TransactionReceipt> executeRegistryPatient(String hexTransaction) throws TransactionException;


        /**
     *
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю.
     * <p>
     * Транзакция позволит зарегистрировать пользователя с адресом account в системе как провайдера.
     * Регистрируемый адрес account должен являться администратором.
     *
     * Если аккаунт уже зарегистрирован и не является администратором, то при обработке транзакции будет вызвана ошибка TransactionException
     * <p>
     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
     * @param account регистрируемый аккаунт
     * @return RawTransaction, которую должен подписать senderAddress
     * @see AccountController#executeRegistryProvider(String)
     */
    public RawTransaction registryProviderRawTransaction(String account);

    /**
     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе registryProviderRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * метод завершится с ошибкой TransactionException.
     * Если аккаунт уже зарегистрирован или не является администратором, то при обработке транзакции будет вызвана ошибка TransactionException.
     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет/
     * @param hexTransaction подписанная RawTransaction
     * @return RemoteCall, вызов которого вернет TransactionReceipt
     * @see AccountController#registryProviderRawTransaction(String)
     */
    public RemoteCall<TransactionReceipt> executeRegistryProvider(String hexTransaction) throws TransactionException;
}
