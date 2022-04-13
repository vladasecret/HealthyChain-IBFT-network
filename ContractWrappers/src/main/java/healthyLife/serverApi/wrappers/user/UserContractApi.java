package healthyLife.serverApi.wrappers.user;

import healthyLife.contractWrappers.user.UserContract;
import healthyLife.serverApi.models.relation.RelationInfoModel;
import healthyLife.serverApi.models.relation.RelationStatus;
import healthyLife.serverApi.wrappers.base.RawContractApi;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;
import java.util.List;

public interface UserContractApi extends RawContractApi {
    public enum PermissionLevel{READ, SHARE, OWNER}

    /**
     *
     * @return возвращает адрес владельца контракта
     */
    public RemoteFunctionCall<String> getOwner();

    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * <p>
     * Транзакция позволит добавить разрешение для указанной записи и указанного пользователя
     * @param permissionsId адрес контракта разрешений PermissionContract, в который необходимо добавить разрешение
     * @param user адрес пользователя, для которого необходимо добавить разрешение
     * @param level уровень доступа, предоставляемый пользователю (PermissionLevel)
     * @param smk закодированный симметричный ключ для расшифровки данных
     * @param encodedHash закодированная ссылка IPFS
     * @see UserContractApi.PermissionLevel
     * @see UserContractApi#executeAddPermission(String)
     */
    public RawTransaction addPermissionRawTransaction(String permissionsId, String user, BigInteger level, byte[] smk, byte[] encodedHash);

    /**
     * Метод выполняет подписанную транзакцию, полученную в методе addPermissionRawTransaction
     * <p>
     * Подписавший транзакцию должен быть владельцем этого контракта UserContract
     * в противном случае вызов RemoteCall завершится ошибкой TransactionException в связи с вызовом revert в контракте
     * <p>
     * Транзакция позволит добавить разрешение для указанной записи и указанного пользователя
     * @param hexTransaction подписанная владельцем контракта RawTransaction
     * @return RemoteCall, вызов которого исполнит транзакцию и вернет информацию о выполнении TransactionReceipt
     * @throws TransactionException вернется в случае несоответствия с RawTransaction, предоставляемой методом addPermissionRawTransaction
     * @see UserContractApi#addPermissionRawTransaction(String, String, BigInteger, byte[], byte[])
     */
    public RemoteCall<TransactionReceipt> executeAddPermission(String hexTransaction) throws TransactionException;


    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * <p>
     * Транзакция позволит отредактировать уровень доступа к данным по permissionsId, который уже был предоставлен пользователю ранее
     * <p> senderAddress должен быть владельцем контракта
     * @param permissionsId адрес контракта PermissionsContract, в котором будут редактироваться права доступа
     * @param user адрес пользователя, для которого будет редактироваться уровень доступа
     * @see UserContractApi#executeEditPermission(String)
     */
    public RawTransaction editPermissionRawTransaction(String permissionsId, String user, BigInteger newLevel);

    /**
     * Метод выполняет подписанную транзакцию, полученную в методе editPermissionRawTransaction
     * <p>
     * Транзакция позволит отредактировать уровень доступа к данным по permissionsId, который уже был предоставлен пользователю ранее
     * <p>
     * Подписавший транзакцию должен быть владельцем этого контракта UserContract
     * в противном случае вызов RemoteCall завершится ошибкой TransactionException в связи с вызовом revert в контракте
     * @param hexTransaction подписанная владельцем контракта RawTransaction
     * @return RemoteCall, вызов которого исполнит транзакцию и вернет информацию о выполнении TransactionReceipt
     * @throws TransactionException вернется в случае несоответствия с RawTransaction, предоставляемой методом confirmRelationRawTransaction
     * @see UserContractApi#confirmRelationRawTransaction(String, Boolean)
     */
    public RemoteCall<TransactionReceipt> executeEditPermission(String hexTransaction) throws TransactionException;


    /**
     * Метод вернет RemoteFunctionCall, результатом которого будет лист всех доступных владельцу метаданных
     * <p>
     * Вызов выполнится от имени senderAddress
     * @return в результате вызова RemoteFunctionCall вернется List элементами которого будут byte[],
     * каждый элемент - строка байт, хранящая адрес, симметричный ключ, ссылку.
     * Массив байт генерируется при помощи abi.encodePacked(permissionsId, smk, encodedHash)
     */
    public RemoteFunctionCall<List> getAllRecordsMetadata();




    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * <p>
     * Транзакция позволит ответить на запрос установления связи, отправленный пользователем user
     * <p> senderAddress должен быть владельцем контракта
     * @param user адрес пользователя, который отправил запрос на установление связи
     * @param confirmStatus статус подтверждения (true - подтвердить, false - отклонить)
     * @see UserContractApi#executeConfirmRelation(String)
     */
    public RawTransaction confirmRelationRawTransaction(String user, Boolean confirmStatus);

    /**
     * Метод выполняет подписанную транзакцию, полученную в методе confirmRelationRawTransaction
     * <p>
     * Транзакция позволит ответить на запрос установления связи, отправленный пользователем user
     * <p>
     * Подписавший транзакцию должен быть владельцем этого контракта UserContract
     * в противном случае вызов RemoteCall завершится ошибкой TransactionException в связи с вызовом revert в контракте
     * @param hexTransaction подписанная владельцем контракта RawTransaction
     * @return RemoteCall, вызов которого исполнит транзакцию и вернет информацию о выполнении TransactionReceipt
     * @throws TransactionException вернется в случае несоответствия с RawTransaction, предоставляемой методом confirmRelationRawTransaction
     * @see UserContractApi#confirmRelationRawTransaction(String, Boolean)
     */
    public RemoteCall<TransactionReceipt> executeConfirmRelation(String hexTransaction) throws TransactionException;

    /**
     * Метод проверяет активна ли связь между владельцем контракта и указанным в параметрах пользователем
     * @param user адрес пользователя
     */
    public RemoteFunctionCall<Boolean> hasActiveRelation(String user);

    /**
     * @param user адрес пользователя
     * @return метод возвращает статус связи владельца с указанным пользователем. Результат необходимо преобразовать в RelationStatus
     * @see RelationStatus
     */
    public RemoteFunctionCall<BigInteger> getRelationStatus(String user);

    /**
     * Метод вернет всю информацию об отношениях, хранящуюся в RelationsContract.
     * <p>
     * @return Элементом List Tuple2. Элементы String - адрес пользователя, UserContractApi.RelationStatus - статус связи с этим пользователем
     * byte[] будет получен при помощи abi.encodePacked(user, uint256(relation.status)),
     * где user - адрес пользователя, status - статус отношений с этим пользователем
     * @see RelationStatus
     *
     */
    public RemoteFunctionCall<List<RelationInfoModel>> getAllRelations();

    /**
     *
     * @return вызов RemoteFunctionCall вернет List,
     * элементами которого будут адреса пользователей системы, которые добавляли записи в мед. карту пользователя (или пациенты врача)
     */
    public RemoteFunctionCall<List> getAssociatedUsers();

    /**
     *
     * @return вызов RemoteFunctionCall вернет List, элементами которого будут адреса, которым senderAddress отправил запрос на установление связи
     */
    public RemoteFunctionCall<List> getInitialedRelations();

    /**
     *
     * @param permissionsAddress
     * @return вызов RemoteFunctionCall вернет кортеж (byte[] smk, byte[] encodedHash),
     * где smk - зашифрованный симметричный ключ, encodedHash - зашифрованная ссылка IPFS, которые хранятся для записи по id permissionsAddress,
     * если данные для senderAddress не существуют, то вернется ошибка
     */
    public RemoteFunctionCall<Tuple2<byte[], byte[]>> getRecordMetadata(String permissionsAddress);


    /**
     * Метод вернет список всех записей, связанных с associatedUser
     * (если UserContract - контракт врача, то associatedUser - пациент, данные которого мы хотим получить.
     * Если UserContract - контракт пациента, то associatedUser - врач, который создал возвращаемые записи.)
     * @param associatedUser адрес пользователя
     * @return вызов RemoteFunctionCall вернет List, элементами которого будет byte[].
     * byte[] будет получен при помощи abi.encodePacked(permissionsId, smk, encodedHash),
     *      * где user - адрес пользователя, status - статус отношений с этим пользователем
     */
    public RemoteFunctionCall<List> getRecordsMetadataByUser(String associatedUser);

    /**
     *
     * @return вызов RemoteFunctionCall вернет список адресов, которые отправили владельцу контракта запрос на установление связи
     */
    public RemoteFunctionCall<List<String>> getRelationRequests();

    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * <p>
     * Транзакция позволит отправить запрос на установление связи с аккаунтом, указанным в параметре user
     * <p> senderAddress должен быть владельцем контракта
     * @param user адрес пользователя, которому отправляем запрос на установление связи
     * @see UserContract#executeInitRelation(String)
     */
    public RawTransaction initRelationRawTransaction(String user);

    /**
     * Метод выполняет подписанную транзакцию, полученную в методе initRelationRawTransaction
     * <p>
     * Транзакция позволит отправить запрос на установление связи с другим аккаунтом
     * <p>
     * Подписавший транзакцию должен быть владельцем этого контракта UserContract
     * в противном случае вызов RemoteCall завершится ошибкой TransactionException в связи с вызовом revert в контракте
     * @param hexTransaction подписанная владельцем контракта RawTransaction
     * @return RemoteCall, вызов которого исполнит транзакцию и вернет информацию о выполнении TransactionReceipt
     * @throws TransactionException вернется в случае несоответствия с RawTransaction, предоставляемой методом confirmRelationRawTransaction
     * @see UserContract#initRelationRawTransaction(String)
     */
    public RemoteCall<TransactionReceipt> executeInitRelation(String hexTransaction) throws TransactionException;

    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * <p>
     * Транзакция позволит отклонить связь с пользователем user
     * <p> senderAddress должен быть владельцем контракта
     * @param user адрес пользователя, с которым отправитель транзакции разрывает связь
     * @see UserContract#executeInitRelation(String)
     */
    public RawTransaction rejectRelationRawTransaction(String user);

    /**
     * Метод выполняет подписанную транзакцию, полученную в методе rejectRelationRawTransaction
     * <p>
     * Транзакция позволит отклонить связь с пользователем user
     * <p>
     * Подписавший транзакцию должен быть владельцем этого контракта UserContract
     * в противном случае вызов RemoteCall завершится ошибкой TransactionException в связи с вызовом revert в контракте
     * @param hexTransaction подписанная владельцем контракта RawTransaction
     * @return RemoteCall, вызов которого исполнит транзакцию и вернет информацию о выполнении TransactionReceipt
     * @throws TransactionException вернется в случае несоответствия с RawTransaction, предоставляемой методом confirmRelationRawTransaction
     * @see UserContract#rejectRelationRawTransaction(String)
     */
    public RemoteCall<TransactionReceipt> executeRejectRelation(String hexTransaction) throws TransactionException;

    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * <p>
     * Транзакция позволит удалить разрешение для указанного адреса пользователя user в контракте разрешений по адресу permissionsId
     * <p> senderAddress должен быть владельцем контракта
     * @param user адрес пользователя, с которым отправитель транзакции разрывает связь
     * @see UserContract#executeRemovePermission(String)
     */
    public RawTransaction removePermissionRawTransaction(String permissionsId, String user);

    /**
     * Метод выполняет подписанную транзакцию, полученную в методе removePermissionRawTransaction
     * <p>
     * Транзакция позволит удалить разрешение для указанного адреса пользователя user в контракте разрешений по адресу permissionsId
     * <p>
     * Подписавший транзакцию должен быть владельцем этого контракта UserContract
     * в противном случае вызов RemoteCall завершится ошибкой TransactionException в связи с вызовом revert в контракте
     * @param hexTransaction подписанная владельцем контракта RawTransaction
     * @return RemoteCall, вызов которого исполнит транзакцию и вернет информацию о выполнении TransactionReceipt
     * @throws TransactionException вернется в случае несоответствия с RawTransaction, предоставляемой методом confirmRelationRawTransaction
     * @see UserContract#removePermissionRawTransaction(String, String)
     */
    public RemoteCall<TransactionReceipt> executeRemovePermission(String hexTransaction) throws TransactionException;
}
