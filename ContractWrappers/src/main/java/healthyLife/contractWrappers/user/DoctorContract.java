package healthyLife.contractWrappers.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import healthyLife.serverApi.models.permission.RecordMetadata;
import healthyLife.serverApi.wrappers.user.DoctorContractApi;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.exceptions.ContractCallException;

@SuppressWarnings("rawtypes")
public class DoctorContract extends UserContract implements DoctorContractApi {

    public static final String FUNC_ADDRECORD = "addRecord";

    protected DoctorContract(String contractAddress, String senderAddress, Web3j web3j){
        super(contractAddress, senderAddress, web3j);
    }

    public static DoctorContract load(String contractAddress, String senderAddress, Web3j web3j){
        if(!hasCode(contractAddress, web3j)){
            throw new ContractCallException("There is no smart contract at the specified address");
        }
        return new DoctorContract(contractAddress, senderAddress, web3j);
    }


    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * <p>
     * Транзакция позволит врачу добавить новую запись в медицинскую карту пациента. 
     * patientData, doctorData, providerData - листы, состоящие из 2 элементов, 1 - й элемент это smk симметричный ключ, 2 - encodedHash, 
     * зашифрованные для пациента, врача и поставщика соответственно.
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
                                                            RecordMetadata providerMetadata) {
        final Function function = new Function(
                FUNC_ADDRECORD,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, patientAddress),
                        patientMetadata,
                        doctorMetadata,
                        new org.web3j.abi.datatypes.Address(160, providerAddress),
                        providerMetadata),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }

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
     * @see DoctorContract#addRecordRawTransaction(String, RecordMetadata, RecordMetadata, String, RecordMetadata) 
     */
    public RemoteCall<TransactionReceipt> executeAddRecord(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_ADDRECORD,
                Arrays.<Type>asList(Address.DEFAULT,
                        new RecordMetadata(Bytes32.DEFAULT, Bytes32.DEFAULT),
                        new RecordMetadata(Bytes32.DEFAULT, Bytes32.DEFAULT),
                        Address.DEFAULT,
                        new RecordMetadata(Bytes32.DEFAULT, Bytes32.DEFAULT)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }
 
}
