package healthyLife.contractWrappers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import healthyLife.contractWrappers.base.RawContract;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.exceptions.ContractCallException;

public class NodeController extends RawContract {

    public static final String FUNC_ADD = "add";

    public static final String FUNC_CONNECTIONALLOWED = "connectionAllowed";

    public static final String FUNC_COUNTADDRESS = "countAddress";

    public static final String FUNC_GETSENDERPROPOSAL = "getSenderProposal";

    public static final String FUNC_GETVOTERS = "getVoters";

    public static final String FUNC_REMOVE = "remove";

    public static final String FUNC_REVOKEPROPOSAL = "revokeProposal";

    public static final String FUNC_SETIDONLYMODE = "setIdOnlyMode";

    public static final String FUNC_SETNODEINGRESS = "setNodeIngress";

    public static final String FUNC_SETSTORAGE = "setStorage";

    public static final Event NODEADDED_EVENT = new Event("NodeAdded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint16>() {}));
    ;

    public static final Event NODEREMOVED_EVENT = new Event("NodeRemoved",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint16>() {}));
    ;

    public static final Event PROPOSALADDED_EVENT = new Event("ProposalAdded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event PROPOSALREMOVED_EVENT = new Event("ProposalRemoved",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Address>() {}));
    ;

    protected NodeController(String contractAddress, String senderAddress, Web3j web3j){
        super(contractAddress, senderAddress, web3j);
    }

    /**
     * Метод создает обертку для контракта NodeController, размещенного по указанному адресу.
     * Метод не проверяет действительно ли по указанному адресу находится необходимый контракт.
     * Он делает проверку, что по указанному адресу хранится какой-либо код
     * @param contractAddress адрес загружаемого контракта
     * @param senderAddress адрес отправителя, который будет подставляться при вызове eth_call (чтение данных из блокчейна)
     * @param web3j
     * @return NodeController
     */
    public static NodeController load(String contractAddress, String senderAddress, Web3j web3j){
        if(!hasCode(contractAddress, web3j)){
            throw new ContractCallException("There is no smart contract at the specified address");
        }
        return new NodeController(contractAddress, senderAddress, web3j);
    }

    /** Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * Транзакция позволит проголосовать за добавление ноды в белый список
     * Если нода уже зарегистрирована или отправитель уже проголосовал за ее добавление, то ничего не произойдет
     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
     * @param enodeId публичный ключ регистрируемой ноды
     * @param enodeHost ip-адрес ноды
     * @param enodePort порт ноды
     * @return RawTransaction транзакция для подписания приватным ключом senderAddress
     * @see NodeController#executeAdd(String)
     */
    public RawTransaction addRawTransaction(String enodeId, String enodeHost, BigInteger enodePort) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADD,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId),
                        new org.web3j.abi.datatypes.Utf8String(enodeHost),
                        new org.web3j.abi.datatypes.generated.Uint16(enodePort)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }


    /**
     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе addRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * метод завершится с ошибкой TransactionException
     * Если нода уже зарегистрирована или отправитель уже проголосовал за ее добавление, то ничего не произойдет
     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет
     * @param hexTransaction
     * @return RemoteCall, вызов которого вернет TransactionReceipt
     * @throws TransactionException
     * @see NodeController#addRawTransaction(String, String, BigInteger)
     */
    public RemoteCall<TransactionReceipt> executeAdd(String hexTransaction) throws TransactionException {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADD,
                Arrays.<Type>asList(Utf8String.DEFAULT,
                        Utf8String.DEFAULT,
                        Uint16.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }

    /**
     * Метод проверяет добавлена ли указанная нода в белый список
     * @param enodeId публичный ключ регистрируемой ноды
     * @param enodeHost ip-адрес ноды
     * @param enodePort порт ноды
     * @return RemoteFunctionCall - после вызова возвращает результат Boolean
     */
    public RemoteFunctionCall<Boolean> connectionAllowed(String enodeId, String enodeHost, BigInteger enodePort) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CONNECTIONALLOWED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId),
                        new org.web3j.abi.datatypes.Utf8String(enodeHost),
                        new org.web3j.abi.datatypes.generated.Uint16(enodePort)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    /**
     * Метод возращает голос отправителя относительно указанной ноды
     *
     * @param enodeId публичный ключ регистрируемой ноды
     * @param enodeHost ip-адрес ноды
     * @param enodePort порт ноды
     * @return RemoteFunctionCall после вызова возвращает результат BigInteger, который можно преобразовать в ProposalStatus
     * @see ProposalStatus
     */
    public RemoteFunctionCall<BigInteger> getSenderProposal(String enodeId, String enodeHost, BigInteger enodePort) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSENDERPROPOSAL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId),
                        new org.web3j.abi.datatypes.Utf8String(enodeHost),
                        new org.web3j.abi.datatypes.generated.Uint16(enodePort)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    /**
     * Метод возвращает список адресов, проголосовавших за указанную ноду
     * @param enodeId публичный ключ регистрируемой ноды
     * @param enodeHost ip-адрес ноды
     * @param enodePort порт ноды
     * @return RemoteFunctionCall после вызова возвращает результат List
     */
    public RemoteFunctionCall<List> getVoters(String enodeId, String enodeHost, BigInteger enodePort) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVOTERS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId),
                        new org.web3j.abi.datatypes.Utf8String(enodeHost),
                        new org.web3j.abi.datatypes.generated.Uint16(enodePort)),
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

    /** Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * Транзакция позволит проголосовать за удаление ноды из белого списка
     * Если нода не зарегистрирована или отправитель уже проголосовал за ее удаление, то ничего не произойдет
     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
     * @param enodeId публичный ключ регистрируемой ноды
     * @param enodeHost ip-адрес ноды
     * @param enodePort порт ноды
     * @return RawTransaction транзакция для подписания приватным ключом senderAddress
     * @see NodeController#executeRemove(String)
     */
    public RawTransaction removeRawTransaction(String enodeId, String enodeHost, BigInteger enodePort) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
            FUNC_REMOVE,
            Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId),
                    new org.web3j.abi.datatypes.Utf8String(enodeHost),
                    new org.web3j.abi.datatypes.generated.Uint16(enodePort)),
            Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }

    /**
     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе removeRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * метод завершится с ошибкой TransactionException
     * Если нода не зарегистрирована или отправитель уже проголосовал за ее удаление, то ничего не произойдет
     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет
     * @param hexTransaction
     * @return RemoteCall после вызова возвращает TransactionReceipt
     * @throws TransactionException
     * @see NodeController#removeRawTransaction(String, String, BigInteger)
     */
    public RemoteCall<TransactionReceipt> executeRemove(String hexTransaction) throws TransactionException {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVE,
                Arrays.<Type>asList(Utf8String.DEFAULT,
                        Utf8String.DEFAULT,
                        Uint16.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction,function);
    }

    /** Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * Транзакция позволит отозвать свой голос относительно указанной ноды
     * Если отправитель не голосовал за указанную ноду, то ничего не произойдет
     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
     * @param enodeId публичный ключ регистрируемой ноды
     * @param enodeHost ip-адрес ноды
     * @param enodePort порт ноды
     * @return RawTransaction транзакция для подписания приватным ключом senderAddress
     * @see NodeController#executeRevokeProposal(String)
     */
    public RawTransaction revokeProposalRawTransaction(String enodeId, String enodeHost, BigInteger enodePort) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEPROPOSAL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId),
                        new org.web3j.abi.datatypes.Utf8String(enodeHost),
                        new org.web3j.abi.datatypes.generated.Uint16(enodePort)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }

    /**
     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе revokeProposalRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * метод завершится с ошибкой TransactionException
     * Если отправитель не голосовал за указанную ноду, то ничего не произойдет
     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет
     * @param hexTransaction
     * @return RemoteCall после вызова возвращает TransactionReceipt
     * @throws TransactionException
     * @see NodeController#revokeProposalRawTransaction(String, String, BigInteger)
     */
    public RemoteCall<TransactionReceipt> executeRevokeProposal(String hexTransaction) throws TransactionException {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEPROPOSAL,
                Arrays.<Type>asList(Utf8String.DEFAULT,
                        Utf8String.DEFAULT,
                        Uint16.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }


    /** Метод возвращает RawTransaction, которую необходимо подписать отправителю, указанному как senderAddress.
     * Транзакция позволит установить настройку использования только публичного адреса узла (по умолчанию true)
     * Адрес senderAddress должен подписать транзакцию и должен являться администратором
     * @return RawTransaction транзакция для подписания приватным ключом senderAddress
     * @see NodeController#executeSetIdOnlyMode(String)
     */
    public RawTransaction setIdOnlyModeRawTransaction(Boolean value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETIDONLYMODE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(value)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }

    /**
     * Метод отправляет подписанную транзакцию, которая должна быть получена в методе revokeProposalRawTransaction.
     * При вызове RemoteCall происходит проверка полей транзакции, в случае их несоответствия (попытки подмены кода)
     * метод завершится с ошибкой TransactionException
     * Транзакция позволит установить настройку использования только публичного адреса узла (по умолчанию true)
     * Чтобы транзакция выполнилась успешно, подписывающий должен быть администратором. В противном случае транзакция выполнена не будет
     * @param hexTransaction
     * @return RemoteCall после вызова возвращает TransactionReceipt
     * @throws TransactionException
     * @see NodeController#setIdOnlyModeRawTransaction(Boolean)
     */
    public RemoteCall<TransactionReceipt> executeSetIdOnlyMode(String hexTransaction) throws TransactionException {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETIDONLYMODE,
                Arrays.<Type>asList(Bool.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }

    public static class NodeAddedEventResponse extends BaseEventResponse {
        public Boolean added;

        public String enodeId;

        public String enodeHost;

        public BigInteger enodePort;
    }

    public static class NodeRemovedEventResponse extends BaseEventResponse {
        public Boolean removed;

        public String enodeId;

        public String enodeHost;

        public BigInteger enodePort;
    }

    public static class ProposalAddedEventResponse extends BaseEventResponse {
        public BigInteger key;

        public BigInteger status;

        public String sender;
    }

    public static class ProposalRemovedEventResponse extends BaseEventResponse {
        public BigInteger key;

        public BigInteger status;

        public String sender;
    }


    public enum ProposalStatus {NONE, ADD, REMOVE};

}
