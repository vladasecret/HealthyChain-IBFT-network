import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.contractWrapper.RegistryContract;
import org.web3j.contractWrapper.Util.Account;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static final BigInteger GAS_PRICE = BigInteger.ZERO;
    static final BigInteger GAS_LIMIT = BigInteger.valueOf(99999999999L);

    static final String privateKey = "c82aa158c161b6c4fe7c9658a09a245ed069ec79ec30dfbbb8bd199c3f1e2d45";
    static final Credentials credentials = Credentials.create(privateKey);
    static final String URL = "http://localhost:7545/";


    public static void main(String[] args) throws Exception {

        Web3j web3j = Web3j.build(new HttpService(URL));

        //BlockchainRegistry registry = new BlockchainRegistry(web3j, credentials);

        Account userAccount = new Account(privateKey, web3j);

        String signedTransaction = userAccount.signTransaction(RegistryContract.createContractRawTransaction(userAccount.getAddress(), web3j));

        RegistryContract registryContract = null;
        try {
            registryContract = RegistryContract.deploy(signedTransaction, web3j).send();
        }
        catch (TransactionException exc){
            exc.printStackTrace();
            System.exit(1);
        }
        System.out.printf("RegistryContract address is: %s%n", registryContract.getContractAddress());

        for (int i = 0; i < 10; ++i){
            DefaultBlockParameter param = DefaultBlockParameter.valueOf(DefaultBlockParameterName.LATEST.name());
            String data = web3j
                    .ethGetStorageAt(registryContract.getContractAddress(), BigInteger.valueOf(i), param).send().getData();
            System.out.printf("[%d]\t%s%n", i, data);
        }

        //UserContractFactory userContractFactory = UserContractFactory.deploy(web3j, credentials, new StaticGasProvider(BigInteger.ZERO, BigInteger.valueOf(99999999L)), registryContract.getContractAddress()).send();

        // EXAMPLE FOR ETH_CALL FUNCTION (DO NOT CHANGE THE BLOCKCHAIN)
//        byte[] adminContractNameBytes = registryContract.ADMIN_CONTRACT().send();
//        System.out.println(adminContractNameBytes);
//        hexTransaction = userAccount.signTransaction(Admin.createContractTransaction(userAccount.getAddress(), web3j, registryContract.getContractAddress()));
//        Admin admin = Admin.deploy(hexTransaction, web3j);

        //EXAMPLE FOR TRANSACTIONS (CHANGE THE BLOCKCHAIN)
        byte[] accountControllerName = registryContract.ACCOUNT_CONTROLLER_CONTRACT().send();
        registryContract.getContractAddress(accountControllerName);

        RawTransaction rawTransaction = registryContract.setContractAddressRawTransaction(accountControllerName, Address.DEFAULT.toString());
        Function function = new org.web3j.abi.datatypes.Function(
                RegistryContract.FUNC_SETCONTRACTADDRESS,
                Arrays.<Type>asList(org.web3j.abi.datatypes.generated.Bytes32.DEFAULT,
                         org.web3j.abi.datatypes.Address.DEFAULT),
                Collections.<TypeReference<?>>emptyList());


        System.out.println("Hex data section: " + rawTransaction.getData().substring(0, 8));
        System.out.println("Defaul function hex data: " + FunctionEncoder.encode(function).substring(2, 10));
        System.out.println();
        web3j.shutdown();
    }

    public static int ascSeq(int[] arr){
        if (arr.length == 0)
            return 0;
        int maxLen = 0;
        int curLen = 1;
        for (int i = 0; i < arr.length - 1; ++i){
            if (arr[i] <= arr[i + 1]){
                curLen++;
            }
            else {
                if (curLen > maxLen)
                    maxLen = curLen;
                curLen = 1;
            }
        }
        if (curLen > maxLen)
            maxLen = curLen;
        return maxLen;
    }
}
