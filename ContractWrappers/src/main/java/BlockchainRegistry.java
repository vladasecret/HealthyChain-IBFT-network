import org.web3j.abi.datatypes.Address;
import org.web3j.contractWrapper.generated.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class BlockchainRegistry {
    static final BigInteger GAS_PRICE = BigInteger.ZERO;
    static final BigInteger GAS_LIMIT = BigInteger.valueOf(99999999999L);

    private Web3j web3j;
    private Credentials adminCredentials;

    RegistryContract registryContract;

    Admin adminContract;
    NodeController nodeController;

    AccountController accountController;
    UserContractFactory userContractFactory;

    public BlockchainRegistry(Web3j web3j, Credentials adminCredentials){
        this.web3j = web3j;
        this.adminCredentials = adminCredentials;
    }

    public void init() throws Exception {
//         var future = CompletableFuture.supplyAsync(() -> {
//             System.out.println("start");
//             return "hello";
//         });
//         Thread.currentThread().sleep(30);
//         System.out.println("After future creation");
//         future.get();
        registryContract = RegistryContract.deploy(web3j, adminCredentials, getGasProvider()).send();
        var adminContractFuture = addAminContractAsync();
        var nodeControllerFuture = addNodeControllerAsync();
//        var accountControllerFuture = addAccountControllerAsync();
//
        CompletableFuture<TransactionReceipt>[] futures = new CompletableFuture[]{adminContractFuture, nodeControllerFuture, /* accountControllerFuture*/};
//        CompletableFuture.allOf(futures).get();
//
        for (var future: futures) {
            TransactionReceipt receipt = future.get();
            if (!receipt.isStatusOK()){
                throw new TransactionException(receipt.getRevertReason(), receipt);
            }
            else
                System.out.println(receipt.toString());
        }


    }



    private CompletableFuture<TransactionReceipt> addAminContractAsync(){
        return Admin
                .deploy(web3j, adminCredentials, getGasProvider(), registryContract.getContractAddress())
                .sendAsync()
                .thenCompose(admin -> {
                    adminContract = admin;
                    return registryContract.ADMIN_CONTRACT().sendAsync();
                })
                .thenCompose(adminContractName -> {
                    return registryContract.setContractAddress(adminContractName, adminContract.getContractAddress())
                            .sendAsync();
                });
    }

    private CompletableFuture<TransactionReceipt> addAccountControllerAsync(){
        return UserContractFactory
                .deploy(web3j, adminCredentials, getGasProvider(), registryContract.getContractAddress())
                .sendAsync()
                .thenCompose(userContractFactory -> {
                    this.userContractFactory = userContractFactory;
                    return AccountController
                            .deploy(web3j, adminCredentials, getGasProvider(), registryContract.getContractAddress(), userContractFactory.getContractAddress())
                            .sendAsync();
                })
                .thenCompose(accountController -> {
                    this.accountController = accountController;
                    return registryContract.ACCOUNT_CONTROLLER_CONTRACT().sendAsync();
                })
                .thenCompose(accountControllerName -> {
                    return registryContract.setContractAddress(accountControllerName, accountController.getContractAddress())
                            .sendAsync();
                });
    }

    private CompletableFuture<TransactionReceipt> addNodeControllerAsync(){
        return NodeController
                .deploy(web3j, adminCredentials, getGasProvider(), registryContract.getContractAddress(), Address.DEFAULT.getValue())
                .sendAsync()
                .thenCompose(controller -> {
                    nodeController = controller;
                    return registryContract.NODE_CONTROLLER_CONTRACT().sendAsync();
                    //return registryContract.setContractAddress(registryContract.NODE_CONTROLLER_CONTRACT().sendAsync(), nodeController.getContractAddress()).sendAsync();
                })
                .thenCompose(nodeControllerName -> {
                    return registryContract.setContractAddress(nodeControllerName, nodeController.getContractAddress())
                            .sendAsync();
                });
    }

    private ContractGasProvider getGasProvider(){
        return new StaticGasProvider(GAS_PRICE, GAS_LIMIT);
    }

}
