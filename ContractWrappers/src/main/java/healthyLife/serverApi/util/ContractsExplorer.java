package healthyLife.serverApi.util;

import healthyLife.contractWrappers.RegistryContract;
import healthyLife.contractWrappers.base.RawContract;
import healthyLife.contractWrappers.generated.AccountController;
import healthyLife.contractWrappers.generated.UserContractFactory;
import healthyLife.serverApi.wrappers.RegistryContractApi;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;

public class ContractsExplorer {
    public static final String REGISTRY_ADDRESS = "0x0000000000000000000000000000000000007777";

    // Но это не ок, в нормальной сети его нужно спрятать
    private final static String adminPrivateKey = "906d026a8167363a73818dd2332c71e71a63c7aa22c66589f038634524a69f61";
    private Account account;
    private healthyLife.contractWrappers.generated.RegistryContract registryContract;
    Web3j web3j;

    public ContractsExplorer(Web3j web3j, String adminPrivateKey) throws IOException {
        this.web3j = web3j;
        if(web3j.ethGetCode(REGISTRY_ADDRESS, DefaultBlockParameterName.LATEST).send().getCode().isEmpty()){
            throw new RuntimeException("Registry contract must be allocated in the genesis file");
        }
        account = new Account(adminPrivateKey, web3j);

        RegistryContractApi registryContract = RegistryContract.load(REGISTRY_ADDRESS, account.getAddress(), web3j);
    }

    public ContractsExplorer(Web3j web3j) throws IOException {
        this(web3j, adminPrivateKey);
    }

    public boolean isInit() throws Exception {
        return registryContract.hasContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT().send()).send();
    }

    public void init() throws Exception {
        if (isInit())
            return;
        UserContractFactory userContractFactory = UserContractFactory.deploy(
                web3j,
                account.credentials,
                new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT),
                REGISTRY_ADDRESS)
                .send();

        AccountController accountController = AccountController.deploy(
                web3j,
                account.credentials,
                new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT),
                REGISTRY_ADDRESS,
                userContractFactory.getContractAddress())
                .send();
        registryContract.setContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT().send(), accountController.getContractAddress());
    }
}
