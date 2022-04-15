package healthyLife.serverApi.wrappers.user;

import healthyLife.contractWrappers.user.UserContract;
import healthyLife.serverApi.models.permission.PermissionInfo;
import healthyLife.serverApi.models.permission.RecordModel;
import healthyLife.serverApi.models.permission.RecordMetadata;
import healthyLife.serverApi.models.relation.RelationInfoModel;
import healthyLife.serverApi.models.relation.RelationStatus;
import healthyLife.serverApi.wrappers.base.RawContractApi;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;

import java.math.BigInteger;
import java.util.List;

public interface UserContractApi extends RawContractApi {


    /**
     *
     * @return возвращает адрес владельца контракта
     */
    public RemoteFunctionCall<String> getOwner();

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
     *
     * @return вызов RemoteFunctionCall вернет список адресов, которые отправили владельцу контракта запрос на установление связи
     */
    public RemoteCall<List<String>> getRelationRequests();

    /**
     * @return вызов RemoteFunctionCall вернет List, элементами которого будут адреса, которым senderAddress отправил запрос на установление связи
     */
    public RemoteCall<List<String>> getInitialedRelations();

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
     *@return Метод проверяет содержится ли в контракте запись с ID permissionsAddress
     */
    public RemoteFunctionCall<Boolean> hasPermission(String permissionsAddress);

    /**
     * Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * <p>
     * Транзакция позволит добавить разрешение для указанной записи и указанного пользователя
     * @param permissionsId адрес контракта разрешений PermissionContract, в который необходимо добавить разрешение
     * @param user адрес пользователя, для которого необходимо добавить разрешение
     * @param level уровень доступа, предоставляемый пользователю (PermissionLevel)
     * @param metadata метаданные записи
     * @see RecordMetadata
     * @see UserContractApi#executeAddPermission(String)
     */
    public RawTransaction addPermissionRawTransaction(String permissionsId, String user, BigInteger level, RecordMetadata metadata);

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
     * @see UserContractApi#addPermissionRawTransaction(String, String, BigInteger, RecordMetadata)
     */
    public RemoteCall<TransactionReceipt> executeAddPermission(String hexTransaction) throws TransactionException;

    /**
     * Получает разрешение для отправителя, хранящееся для записи по указанному ID
     * @param permissionsId адрес PermissionsContract (ID)
     * @see PermissionInfo
     */
    public RemoteFunctionCall<PermissionInfo> getPermissionInfo(String permissionsId);

    /**
     * Получает разрешение для указанного пользователя, хранящееся для записи по указанному ID.
     * Если отправитель хочет получить информацию о другом пользователе, он должен иметь доступ к записи выше, чем ReadOnly.
     * @param permissionsId адрес PermissionsContract (ID)
     * @see PermissionInfo
     */
    public RemoteFunctionCall<PermissionInfo> getPermissionInfo(String permissionsId, String user);

    /**
     * Метод возвращает список ВСЕХ разрешений для указанной по permissionsId записи.
     * Отправитель должен иметь доступ к записи выше, чем ReadOnly.
     * @param permissionsId адрес PermissionsContract (ID)
     * @see PermissionInfo
     */
    public RemoteFunctionCall<List<PermissionInfo>> getAllPermissionsInfo(String permissionsId);

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

    /**
     * Метод возвращает метаданные, хранящиеся в контракте по адресу permissionsAddress, созданные для получателя senderAddress
     * @param permissionsAddress
     * @return вызов RemoteFunctionCall вернет RecordMetadata
     * @see RecordMetadata
     */
    public RemoteFunctionCall<RecordMetadata> getRecordMetadata(String permissionsAddress);

    /**
     * Метод возвращает информацию обо всех записях, доступных владельцу контракта,
     * внутри содержится информация о разрешениях только для отправителя.
     * Если необходимо получить информацию обо всех разрешениях для конкретной записи, используйте permissionId и метод getAllPermissionsInfo
     * @return
     * @see RecordModel
     * @see UserContractApi#getAllPermissionsInfo(String)
     */
    public RemoteFunctionCall<List<RecordModel>> getAllRecords();
}
