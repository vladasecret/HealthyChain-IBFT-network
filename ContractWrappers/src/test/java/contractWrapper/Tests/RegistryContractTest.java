package contractWrapper.Tests;

import contractWrapper.util.Options;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.web3j.EVMTest;
import org.web3j.abi.datatypes.Address;
import healthyLife.serverApi.util.Account;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.exceptions.ContractCallException;

import healthyLife.contractWrappers.RegistryContract;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@EVMTest
public class RegistryContractTest {

    static final String privateKey = "906d026a8167363a73818dd2332c71e71a63c7aa22c66589f038634524a69f61";

    static Web3j web3j;
    static Account account;

    static RegistryContract registryContract;

    @BeforeAll
    public static void initValue() throws Exception {
        web3j = Web3j.build(new HttpService(Options.URL));
        account = new Account(privateKey, web3j);

        RawTransaction createContractTransaction = RegistryContract.createContractRawTransaction(account.getAddress(), web3j);
        registryContract = RegistryContract.deploy(account.signTransaction(createContractTransaction), web3j).send();
        //registryContract = RegistryContract.load("0x0000000000000000000000000000000000007777", account.getAddress(), web3j);
    }

    @Test
    public void deployTest() throws Exception {
        RawTransaction createContractTransaction = RegistryContract.createContractRawTransaction(account.getAddress(), web3j);
        RegistryContract registryContract = RegistryContract.deploy(account.signTransaction(createContractTransaction), web3j).send();
        Assertions.assertNotEquals(Address.DEFAULT.toString(), registryContract.getContractAddress());
    }

    @Test
    public void loadFailureTest() {
        try {
            RegistryContract.load(Address.DEFAULT.toString(), account.getAddress(), web3j);
        }
        catch (ContractCallException exception){
            return;
        }
        fail("Load contract from default address");
    }

    @Test
    public void loadSuccessTest(){
        try {

            String address = registryContract.getContractAddress();
            RegistryContract registryContract = RegistryContract.load(address, account.getAddress(), web3j);
            Assertions.assertEquals(address, registryContract.getContractAddress());
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    public void GetConstantsTest(){
        try {
            byte[] res = registryContract.ADMIN_CONTRACT().send();
            Assertions.assertTrue(new String(res).toLowerCase(Locale.ROOT).startsWith("admins"));

            res = registryContract.NODE_CONTROLLER_CONTRACT().send();
            Assertions.assertTrue(new String(res).toLowerCase(Locale.ROOT).startsWith("nodecontroller"));

            res = registryContract.ACCOUNT_CONTROLLER_CONTRACT().send();
            Assertions.assertTrue(new String(res).toLowerCase(Locale.ROOT).startsWith("accountcontroller"));
        }
        catch (Exception exc){
            fail(exc);
        }
    }

    @Test
    public void hasContractAddressFalseTest(){
        try{
            byte[] randomName = Arrays.copyOf("dfaf".getBytes(StandardCharsets.UTF_8), 32);//

            Assertions.assertFalse(registryContract.hasContractAddress(randomName).send());
        }
        catch (Exception exception){
            fail(exception.getMessage());
        }
    }

    @Test
    public void getContractAddressTest(){
        try {
            byte[] adminName = registryContract.ADMIN_CONTRACT().send();
            String address = registryContract.getContractAddress(adminName).send();
            if (registryContract.hasContractAddress(adminName).send()){
                Assertions.assertNotEquals(Address.DEFAULT.toString(), address);
            }
            else Assertions.assertEquals(Address.DEFAULT.toString(), address);
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    public void getContractAddressZeroNameTest() {
        try{
            byte[] name = new byte[32];
            registryContract.getContractAddress(name).send();
        }
        catch (ContractCallException exc){
            return;
        }
        catch (Exception exc){
            fail(exc.getClass().getName() + ":\t" + exc.getMessage());
        }
    }

    @Test
    public void setContractAddressSuccessTest(){
        byte[] selfName = Arrays.copyOf("self".getBytes(StandardCharsets.UTF_8), 32);
        String signedTx = account
                .signTransaction(registryContract
                        .setContractAddressRawTransaction(selfName, registryContract.getContractAddress()));
        try {
            TransactionReceipt receipt = registryContract.executeSetContractAddress(signedTx).send();
            Assertions.assertTrue(receipt.isStatusOK());

            String selfAddress = registryContract.getContractAddress(selfName).send();
            Assertions.assertEquals(registryContract.getContractAddress(), selfAddress);
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test void setContractAddressFailureTest() throws IOException {
        String notCreatorPrivateKey = "889640e8e485ac54349fe3af4ff652fe4aa8ea31c8ea6c9b9e94b387b855667d";
        try {
            Account notCreator = new Account(notCreatorPrivateKey, web3j);
            RegistryContract registryContractCopy = RegistryContract
                    .load(registryContract.getContractAddress(), notCreator.getAddress(), web3j);

            byte[] selfName = Arrays.copyOf("self".getBytes(StandardCharsets.UTF_8), 32);
            String signedTx = notCreator
                    .signTransaction(registryContractCopy
                            .setContractAddressRawTransaction(selfName, registryContractCopy.getContractAddress()));

            TransactionReceipt receipt = registryContractCopy.executeSetContractAddress(signedTx).send();
            Assertions.assertFalse(receipt.isStatusOK());
            System.out.println(receipt.getRevertReason());
        }
        catch (Exception exc){
            fail(exc);
        }

    }


    @AfterAll
    public static void Shutdown(){
        web3j.shutdown();
    }

}
