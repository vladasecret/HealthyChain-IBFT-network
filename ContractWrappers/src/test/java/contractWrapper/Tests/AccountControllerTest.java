package contractWrapper.Tests;

import contractWrapper.util.Options;
import healthyLife.contractWrappers.generated.AccountController;
import healthyLife.contractWrappers.generated.UserContract;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import healthyLife.contractWrappers.base.RawContract;
import healthyLife.contractWrappers.RegistryContract;
import healthyLife.serverApi.util.Account;
import healthyLife.contractWrappers.generated.UserContractFactory;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.StaticGasProvider;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class AccountControllerTest {

    static final String adminPrivateKey = "c82aa158c161b6c4fe7c9658a09a245ed069ec79ec30dfbbb8bd199c3f1e2d45";
    static final String[] doctorsPrivateKey = new String[]{
            "59ddcb659507b6a247b4e6e4602728a4987c9c39e66e44182c4b334212d67dbc",
            "343ac984ce60d36fcbf800e1af3a7d6b5a0242d156d6ab9edfaac3cbac133c38",
            "fef55a915f9e2261bd8498d9a91e5df99eea44296a87a9b7c966bdd74284b56f"
    };
    static final String[] patientsPrivateKey = new String[]{
            "f1a756d13be8b84872ed0ff0666a3d4cae1b7e62ad62d16b1f445438c6cb8366",
            "1d4bb5760bb89aa3b83d82c6fffcf2a44bc775acec4f8591efa26f55a1ffe844",
            "c3f0aa5f3688dedb2ac7454d62e3daed2e9d65ad43ce06434f8cb8f47c3ff930",
            "8bd8f3e379470aec5c3984267073f9378f001b2e05551835a5d6cff5fb663132",
            "5210fc5e2a94a15dad139726b0646873a904a93a69c437935ef4854c56fc56b6"
    };

    static Web3j web3j;
    static Account admin;

    static RegistryContract registryContract;
    static healthyLife.contractWrappers.AccountController accountController;

    @BeforeAll
    public static void initValue() throws Exception {
        web3j = Web3j.build(new HttpService(Options.URL));

        admin = new Account(adminPrivateKey, web3j);

        RawTransaction createContractTransaction = RegistryContract.createContractRawTransaction(admin.getAddress(), web3j);
        registryContract = RegistryContract.deploy(admin.signTransaction(createContractTransaction), web3j).send();

        UserContractFactory userContractFactory = UserContractFactory
                .deploy(web3j, admin.credentials, new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT),
                        registryContract.getContractAddress())
                .send();

        AccountController accountControllerTemp = AccountController
                .deploy(web3j, admin.credentials, new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT),
                        registryContract.getContractAddress(), userContractFactory.getContractAddress())
                .send();

        accountController = healthyLife.contractWrappers.AccountController.load(accountControllerTemp.getContractAddress(), admin.getAddress(), web3j);

    }

    @Test
    public void getUserContractAddressFailureTest() throws Exception {
        try {
            String userContractAddress = accountController.getUserContractAddress(registryContract.getContractAddress()).send();
        }
        catch (ContractCallException exc){
            System.out.println(exc.getMessage());
            return;
        }
        fail("No exception thrown");
    }

    @Test
    public void registryPatientSentAdminTest(){
        try {
            Account patient = new Account(patientsPrivateKey[0], web3j);
            RawTransaction rawTransaction = accountController.registryPatientRawTransaction(patient.getAddress());
            TransactionReceipt transactionReceipt = accountController.executeRegistryPatient(admin.signTransaction(rawTransaction)).send();
            assertTrue(transactionReceipt.isStatusOK());
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    public void registryPatientAlreadyRegisteredTest(){
        try {
            Account patient = new Account(patientsPrivateKey[1], web3j);
            RawTransaction rawTransaction = accountController.registryPatientRawTransaction(patient.getAddress());
            TransactionReceipt transactionReceipt = accountController.executeRegistryPatient(admin.signTransaction(rawTransaction)).send();
            assertTrue(transactionReceipt.isStatusOK());

            rawTransaction = accountController.registryPatientRawTransaction(patient.getAddress());
            transactionReceipt = accountController.executeRegistryPatient(admin.signTransaction(rawTransaction)).send();
            assertFalse(transactionReceipt.isStatusOK());
            System.out.println(transactionReceipt.getRevertReason());
        }
        catch (ContractCallException exc){
            System.out.println(exc.getMessage());
            return;
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    public void registryPatientSentNotAdminTest(){
        try {
            Account patientSender = new Account(patientsPrivateKey[0], web3j);
            accountController.setSenderAddress(patientSender.getAddress());
            Account registeredPat = new Account(patientsPrivateKey[2], web3j);
            RawTransaction rawTransaction = accountController.registryPatientRawTransaction(registeredPat.getAddress());
            TransactionReceipt transactionReceipt = accountController.executeRegistryPatient(patientSender.signTransaction(rawTransaction)).send();
        }
        catch (ContractCallException exception){
            try {
                if (registryContract.hasContractAddress(registryContract.ADMIN_CONTRACT().send()).send()) {
                    assertTrue(exception.getMessage().toLowerCase(Locale.ROOT).contains("revert"));
                }
            } catch (Exception e) {
                fail(e.getMessage());
            }

        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
        finally {
            accountController.setSenderAddress(admin.getAddress());
        }
    }

    @Test
    public void registryDoctorSentAdminTest(){
        try {
            Account doctor = new Account(doctorsPrivateKey[0], web3j);
            RawTransaction rawTransaction = accountController.registryDoctorRawTransaction(doctor.getAddress());
            TransactionReceipt transactionReceipt = accountController
                    .executeRegistryDoctor(admin.signTransaction(rawTransaction))
                    .send();
            assertTrue(transactionReceipt.isStatusOK());
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }


    @Test
    public void registryDoctorAlreadyRegisteredTest(){
        try {
            Account doctor = new Account(doctorsPrivateKey[1], web3j);
            RawTransaction rawTransaction = accountController.registryDoctorRawTransaction(doctor.getAddress());
            TransactionReceipt transactionReceipt = accountController
                    .executeRegistryDoctor(admin.signTransaction(rawTransaction)).send();
            assertTrue(transactionReceipt.isStatusOK());

            rawTransaction = accountController.registryDoctorRawTransaction(doctor.getAddress());
            transactionReceipt = accountController.executeRegistryDoctor(admin.signTransaction(rawTransaction)).send();
            assertFalse(transactionReceipt.isStatusOK());
            System.out.println(transactionReceipt.getRevertReason());
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    public void registryDoctorSentNotAdminTest() {
        try {
            Account patientSender = new Account(patientsPrivateKey[0], web3j);
            accountController.setSenderAddress(patientSender.getAddress());
            Account registeredDoc = new Account(doctorsPrivateKey[2], web3j);
            RawTransaction rawTransaction = accountController.registryPatientRawTransaction(registeredDoc.getAddress());
            accountController.executeRegistryPatient(patientSender.signTransaction(rawTransaction)).send();
        }
        catch (ContractCallException exception){
            try {
                if (registryContract.hasContractAddress(registryContract.ADMIN_CONTRACT().send()).send()) {
                    assertTrue(exception.getMessage().toLowerCase(Locale.ROOT).contains("revert"));
                }
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
        finally {
            accountController.setSenderAddress(admin.getAddress());
        }
    }


    @Test
    public void registryProviderTest()  {
        RawTransaction rawTransaction = accountController.registryProviderRawTransaction(admin.getAddress());
        try {
            accountController.executeRegistryProvider(admin.signTransaction(rawTransaction)).send();
            healthyLife.contractWrappers.AccountController.UserClass userClass =  healthyLife.contractWrappers.AccountController.UserClass
                    .values()[accountController.getUserClass(admin.getAddress()).send().intValueExact()];
            assertSame(healthyLife.contractWrappers.AccountController.UserClass.PROVIDER, userClass);
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }


    @Test
    public void UserContractOwnersCheck(){
        try {
            Account patient = new Account(patientsPrivateKey[4], web3j);
            assertFalse(accountController.isRegistered(patient.getAddress()).send());

            RawTransaction rawTransaction = accountController.registryPatientRawTransaction(patient.getAddress());
            TransactionReceipt res = accountController.executeRegistryPatient(admin.signTransaction(rawTransaction)).send();
            assertTrue(res.isStatusOK());
            assertTrue(accountController.isRegistered(patient.getAddress()).send());

            String userContractAddress = accountController.getUserContractAddress(patient.getAddress()).send();
            UserContract userContract = UserContract
                    .load(userContractAddress,
                            web3j,
                            patient.credentials,
                            new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT));
            assertEquals(patient.getAddress(), userContract.getOwner().send());
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }


    @AfterAll
    public static void Shutdown(){
        web3j.shutdown();
    }
}
