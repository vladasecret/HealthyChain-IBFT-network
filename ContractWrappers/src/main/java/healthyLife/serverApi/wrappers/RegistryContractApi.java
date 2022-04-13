package healthyLife.serverApi.wrappers;

import healthyLife.contractWrappers.RegistryContract;
import healthyLife.serverApi.wrappers.base.RawContractApi;
import org.web3j.protocol.core.RemoteFunctionCall;

/**
 * @see RegistryContract
 */
public interface RegistryContractApi extends RawContractApi {

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall, вызов которого вернет значение константы ACCOUNT_CONTROLLER_CONTRACT.
     * ACCOUNT_CONTROLLER_CONTRACT - имя контракта AccountController, хранящееся в контракте как bytes32
     * @return RemoteFunctionCall, вызов которого вернет значение константы ACCOUNT_CONTROLLER_CONTRACT в byte[]
     */
    public RemoteFunctionCall<byte[]> ACCOUNT_CONTROLLER_CONTRACT();

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall, вызов которого вернет значение константы ADMIN_CONTRACT.
     * ADMIN_CONTRACT - имя для контракта Admin, хранящееся как bytes32
     *
     * @return RemoteFunctionCall, вызов которого вернет значение константы ADMIN_CONTRACT в byte[]
     */
    public RemoteFunctionCall<byte[]> ADMIN_CONTRACT();

    /**
     * Метод(eth_call) возвращает RemoteFunctionCall, вызов которого вернет значение константы NODE_CONTROLLER_CONTRACT.
     * NODE_CONTROLLER_CONTRACT - имя для контракта NodeController, хранящееся как bytes32
     *
     * @return RemoteFunctionCall, вызов которого вернет значение константы NODE_CONTROLLER_CONTRACT в byte[]
     */
    public RemoteFunctionCall<byte[]> NODE_CONTROLLER_CONTRACT();


    /**
     * Метод(eth_call) возвращает RemoteFunctionCall, вызов которого вернет адрес контракта (String) по его имени.
     * <p>
     * Если для указанного имени не зарегистрирован контракт, то будет возвращено значение Address.DEFAULT ("0x0000000000000000000000000000000000000000")
     * @param name имя контракта в виде byte[32]
     * @return RemoteFunctionCall, который вернет адрес контракта String
     */
    public RemoteFunctionCall<String> getContractAddress(byte[] name);


    /**
     * Метод(eth_call) возвращает RemoteFunctionCall, вызов которого вернет результат проверки является ли указанный адрес администратором
     * или зарегистрирован как пользователь.
     * В случае, если по именам ADMIN_CONTRACT и ACCOUNT_CONTROLLER_CONTRACT не зарегистрированы контракты, то метод вернет true
     * @param account Ethereum адрес пользователя
     * @return RemoteFunctionCall, вызов которого вернет Boolean
     */
    public RemoteFunctionCall<Boolean> isAuthorized(String account);
}
