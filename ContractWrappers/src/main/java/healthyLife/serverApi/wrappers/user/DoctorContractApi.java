package healthyLife.serverApi.wrappers.user;

import healthyLife.serverApi.models.permission.RecordMetadata;
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
     *
     * @param patientAddress адрес пациента, в медицинскую карту которого отправитель добавит запись
     * @param patientMetadata smk, encodedHash для пациента
     * @param doctorMetadata smk, encodedHash для врача
     * @param providerAddress адрес поставщика
     * @param providerMetadata smk, encodedHash для поставщика
     * @see DoctorContractApi#executeAddRecord(String)
     * @see healthyLife.serverApi.models.permission.RecordMetadata
     */
    public RawTransaction addRecordRawTransaction(String patientAddress,
                                                  RecordMetadata patientMetadata,
                                                  RecordMetadata doctorMetadata,
                                                  String providerAddress,
                                                  RecordMetadata providerMetadata);

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
     * @see DoctorContractApi#addRecordRawTransaction(String, RecordMetadata, RecordMetadata, String, RecordMetadata)
     */
    public RemoteCall<TransactionReceipt> executeAddRecord(String hexTransaction) throws TransactionException;
}
