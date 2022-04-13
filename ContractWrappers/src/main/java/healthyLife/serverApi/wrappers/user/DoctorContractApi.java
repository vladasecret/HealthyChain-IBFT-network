package healthyLife.serverApi.wrappers.user;

import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;

import java.util.List;

/**
 * @see healthyLife.contractWrappers.user.DoctorContract
 */
public interface DoctorContractApi extends UserContractApi{
    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * <p>
     * Транзакция позволит врачу добавить новую запись в медицинскую карту пациента.
     * patientData, doctorData, providerData - листы, состоящие из 2 элементов, 1 - й элемент это smk симметричный ключ, 2 - encodedHash,
     * зашифрованные для пациента, врача и поставщика соответственно.
     *
     * @param patientAddress адрес пациента, в медицинскую карту которого отправитель добавит запись
     * @param patientData smk, encodedHash для пациента
     * @param doctorData smk, encodedHash для врача
     * @param providerAddress адрес поставщика
     * @param providerData smk, encodedHash для поставщика
     * @see DoctorContractApi#executeAddRecord(String)
     */
    public RawTransaction addRecordRawTransaction(
            String patientAddress,
            List<byte[]> patientData,
            List<byte[]> doctorData,
            String providerAddress,
            List<byte[]> providerData);

    /**
     * Метод выполняет подписанную транзакцию, полученную в методе addRecordRawTransaction
     * <p>
     * Подписавший транзакцию должен быть владельцем этого контракта UserContract
     * в противном случае вызов RemoteCall завершится ошибкой TransactionException в связи с вызовом revert в контракте
     * <p>
     * Транзакция позволит врачу добавить новую запись в медицинскую карту пациента.
     * @param hexTransaction подписанная владельцем контракта RawTransaction
     * @return RemoteCall, вызов которого исполнит транзакцию и вернет информацию о выполнении TransactionReceipt
     * @throws TransactionException вернется в случае несоответствия с RawTransaction, предоставляемой методом addPermissionRawTransaction
     * @see DoctorContractApi#addRecordRawTransaction(String, List, List, String, List)
     */
    public RemoteCall<TransactionReceipt> executeAddRecord(String hexTransaction) throws TransactionException;
}
