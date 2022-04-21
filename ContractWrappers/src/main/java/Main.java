import healthyLife.contractWrappers.base.RawContract;
import healthyLife.contractWrappers.generated.AccountIngress;
import healthyLife.contractWrappers.generated.NodeIngress;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import healthyLife.contractWrappers.RegistryContract;
import healthyLife.contractWrappers.util.Account;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;


import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static final BigInteger GAS_PRICE = BigInteger.ZERO;
    static final BigInteger GAS_LIMIT = BigInteger.valueOf(99999999999L);

    static final String privateKey = "906d026a8167363a73818dd2332c71e71a63c7aa22c66589f038634524a69f61";
    static final Credentials credentials = Credentials.create(privateKey);
    static final String URL = "http://localhost:8545/";


    public static void main(String[] args) throws Exception {

        Web3j web3j = Web3j.build(new HttpService(URL));

        //BlockchainRegistry registry = new BlockchainRegistry(web3j, credentials);

        Account userAccount = new Account(privateKey, web3j);

//        healthyLife.contractWrappers.generated.RegistryContract registryContract = healthyLife.contractWrappers.generated.RegistryContract
//                .deploy(web3j,
//                        userAccount.credentials,
//                        new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT))
//                .send();

        String registryContractAddress = "0x0000000000000000000000000000000000007777";

        System.out.println("=============RegistryContract=============");
        printContractData(web3j, registryContractAddress);

        AccountIngress accountIngress = AccountIngress
                .deploy(web3j,
                        credentials,
                        new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT))
                .send();
        System.out.println("=============AccountIngress=============");
        printContractData(web3j, accountIngress.getContractAddress());

        NodeIngress nodeIngress = NodeIngress
                .deploy(web3j,
                        credentials,
                        new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT))
                .send();
        System.out.println("=============NodeIngress=============");
        printContractData(web3j, nodeIngress.getContractAddress());

        web3j.shutdown();
    }

    public static void printContractData(Web3j web3j, String contractAddress) throws IOException {
        System.out.printf("Contract address is: %s%n", contractAddress);

        System.out.println("Contract code is:");
        System.out.println(web3j.ethGetCode(contractAddress, DefaultBlockParameterName.LATEST).send().getCode());
        System.out.println();
        for (int i = 0; i < 10; ++i){
            DefaultBlockParameter param = DefaultBlockParameter.valueOf(DefaultBlockParameterName.LATEST.name());
            String data = web3j
                    .ethGetStorageAt(contractAddress, BigInteger.valueOf(i), param).send().getData();
            System.out.printf("[%d]\t%s%n", i, data);
        }
    }
}
