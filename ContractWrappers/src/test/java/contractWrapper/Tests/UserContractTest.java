package contractWrapper.Tests;


import contractWrapper.util.Options;
import healthyLife.contractWrappers.RegistryContract;
import healthyLife.contractWrappers.base.RawContract;
import healthyLife.contractWrappers.generated.AccountController;
import healthyLife.contractWrappers.generated.UserContractFactory;
import healthyLife.contractWrappers.user.UserContract;
import healthyLife.contractWrappers.util.Account;
import healthyLife.serverApi.models.relation.RelationInfoModel;
import healthyLife.serverApi.models.relation.RelationStatus;
import healthyLife.serverApi.wrappers.AccountControllerApi;
import healthyLife.serverApi.wrappers.RegistryContractApi;
import healthyLife.serverApi.wrappers.user.UserContractApi;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.web3j.EVMTest;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Numeric;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@EVMTest
public class UserContractTest {


    static final String providerPrivateKey = "c82aa158c161b6c4fe7c9658a09a245ed069ec79ec30dfbbb8bd199c3f1e2d45";
    static final String doctorPrivateKey = "59ddcb659507b6a247b4e6e4602728a4987c9c39e66e44182c4b334212d67dbc";
    static final String doctor2PrivateKey = "fef55a915f9e2261bd8498d9a91e5df99eea44296a87a9b7c966bdd74284b56f";
    static final String patientPrivateKey = "343ac984ce60d36fcbf800e1af3a7d6b5a0242d156d6ab9edfaac3cbac133c38";

    static Account provider;
    static Account doctor;
    static Account doctor2;
    static Account patient;

    private static Web3j web3j;

    RegistryContractApi registryContract;
    static AccountControllerApi accountController;

    @BeforeAll
    public static void initValue() throws Exception {
        web3j = Web3j.build(new HttpService(Options.URL));

        provider = new Account(providerPrivateKey, web3j);
        doctor = new Account(doctorPrivateKey, web3j);
        doctor2 = new Account(doctor2PrivateKey, web3j);
        patient = new Account(patientPrivateKey, web3j);



        RawTransaction createContractTransaction = RegistryContract.createContractRawTransaction(provider.getAddress(), web3j);
        RegistryContract registryContract = RegistryContract.deploy(provider.signTransaction(createContractTransaction), web3j).send();
        UserContractFactory userContractFactory = UserContractFactory
                .deploy(web3j,
                        provider.credentials,
                        new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT),
                        registryContract.getContractAddress())
                .send();
        healthyLife.contractWrappers.generated.AccountController accountControllerGen = AccountController
                .deploy(web3j,
                        provider.credentials,
                        new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT),
                        registryContract.getContractAddress(),
                        userContractFactory.getContractAddress())
                .send();

        accountController = healthyLife.contractWrappers.AccountController
                .load(accountControllerGen.getContractAddress(),
                        provider.getAddress(), web3j);

         RawTransaction setController = registryContract
                .setContractAddressRawTransaction(registryContract.ACCOUNT_CONTROLLER_CONTRACT().send(), accountController.getContractAddress());

        TransactionReceipt transactionReceipt = registryContract.executeSetContractAddress(provider.signTransaction(setController)).send();
        if (!transactionReceipt.isStatusOK()){
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }

        RawTransaction registryRawTransaction = accountController.registryPatientRawTransaction(patient.getAddress());
        transactionReceipt = accountController
                .executeRegistryPatient(provider.signTransaction(registryRawTransaction))
                .send();
        if (!transactionReceipt.isStatusOK()){
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }

        registryRawTransaction = accountController.registryDoctorRawTransaction(doctor.getAddress());
        transactionReceipt = accountController
                .executeRegistryDoctor(provider.signTransaction(registryRawTransaction))
                .send();
        if (!transactionReceipt.isStatusOK()){
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }

        registryRawTransaction = accountController.registryDoctorRawTransaction(doctor2.getAddress());
        transactionReceipt = accountController
                .executeRegistryDoctor(provider.signTransaction(registryRawTransaction))
                .send();
        if (!transactionReceipt.isStatusOK()){
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }

        registryRawTransaction = accountController.registryProviderRawTransaction(provider.getAddress());
        transactionReceipt = accountController
                .executeRegistryProvider(provider.signTransaction(registryRawTransaction))
                .send();
        if (!transactionReceipt.isStatusOK()){
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }
    }

    @Test
    public void isRegisteredTest(){
        try {
            assertTrue(accountController.isRegistered(patient.getAddress()).send());
            healthyLife.contractWrappers.generated.UserContract userContract = healthyLife.contractWrappers.generated.UserContract
                    .load(accountController.getUserContractAddress(patient.getAddress()).send(),
                            web3j,
                            patient.credentials,
                            new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT));
            assertEquals(patient.getAddress(), userContract.getOwner().send());

            assertTrue(accountController.isRegistered(doctor.getAddress()).send());
            assertTrue(accountController.isRegistered(provider.getAddress()).send());
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    public void InitAndConfirmRelationTest(){
        try {
            String patientContractAddress = accountController.getUserContractAddress(patient.getAddress()).send();
            UserContractApi patientContract = UserContract.load(patientContractAddress, patient.getAddress(), web3j);

            String doctorContractAddress = accountController.getUserContractAddress(doctor.getAddress()).send();
            UserContractApi doctorContract = UserContract.load(doctorContractAddress, doctor.getAddress(), web3j);

            RawTransaction initRelationRaw = patientContract.initRelationRawTransaction(doctor.getAddress());
            TransactionReceipt initTransactionReceipt = patientContract
                    .executeInitRelation(patient.signTransaction(initRelationRaw))
                    .send();
            assertTrue(initTransactionReceipt.isStatusOK());

            List<String> requests = doctorContract.getRelationRequests().send();
            assertTrue(requests.contains(patient.getAddress()), "contract doesn't contain request");

            RawTransaction confirmRaw = doctorContract.confirmRelationRawTransaction(requests.get(0), true);
            TransactionReceipt confirmReceipt = doctorContract.executeConfirmRelation(doctor.signTransaction(confirmRaw)).send();
            String reason = confirmReceipt.isStatusOK() ? "" :
                    new String(Numeric.hexStringToByteArray(Numeric.cleanHexPrefix(confirmReceipt.getRevertReason())));
            assertTrue(confirmReceipt.isStatusOK(), reason);
            assertTrue(patientContract.hasActiveRelation(doctor.getAddress()).send());
            assertTrue(doctorContract.hasActiveRelation(patient.getAddress()).send());

            confirmRaw = doctorContract.confirmRelationRawTransaction(patient.getAddress(), false);
            TransactionReceipt confirmReciept = doctorContract.executeConfirmRelation(doctor.signTransaction(confirmRaw)).send();

            assertFalse(confirmReciept.isStatusOK());
        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    public void getAllRelationsTest(){
        try {
            String patientContractAddress = accountController.getUserContractAddress(patient.getAddress()).send();
            UserContractApi patientContract = UserContract.load(patientContractAddress, patient.getAddress(), web3j);

            String doctorContractAddress = accountController.getUserContractAddress(provider.getAddress()).send();
            UserContractApi providerContract = UserContract.load(doctorContractAddress, provider.getAddress(), web3j);

            RawTransaction initRawTransaction = patientContract.initRelationRawTransaction(provider.getAddress());
            TransactionReceipt transactionReceipt = patientContract
                    .executeInitRelation(patient.signTransaction(initRawTransaction))
                    .send();
            assertTrue(transactionReceipt.isStatusOK(), "Init relation failure");

            List<RelationInfoModel> allRelations
                    = providerContract.getAllRelations().send();
            assertTrue(allRelations.contains(new RelationInfoModel(patient.getAddress(), RelationStatus.REQUESTED)));

        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    public void RejectActiveRelationTest(){
        try{
            String patientContractAddress = accountController.getUserContractAddress(patient.getAddress()).send();
            UserContractApi patientContract = UserContract.load(patientContractAddress, patient.getAddress(), web3j);

            String doctorContractAddress = accountController.getUserContractAddress(doctor2.getAddress()).send();
            UserContractApi doctorContract = UserContract.load(doctorContractAddress, doctor2.getAddress(), web3j);

            RawTransaction initRawTransaction = patientContract.initRelationRawTransaction(doctor2.getAddress());
            TransactionReceipt transactionReceipt = patientContract
                    .executeInitRelation(patient.signTransaction(initRawTransaction))
                    .send();
            assertTrue(transactionReceipt.isStatusOK(), "Init relation failure");
            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
                    RelationStatus.REQUESTED);
            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
                    RelationStatus.INITIALED);

            RawTransaction confirmRawTransaction = doctorContract.confirmRelationRawTransaction(patient.getAddress(), true);
            TransactionReceipt confirmReceipt = doctorContract.executeConfirmRelation(doctor2.signTransaction(confirmRawTransaction)).send();
            assertTrue(confirmReceipt.isStatusOK());

            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
                    RelationStatus.ACTIVE);
            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
                    RelationStatus.ACTIVE);

            RawTransaction rejectRawTransaction = patientContract.rejectRelationRawTransaction(doctor2.getAddress());
            TransactionReceipt rejectReceipt = patientContract
                    .executeRejectRelation(patient.signTransaction(rejectRawTransaction))
                    .send();
            assertTrue(rejectReceipt.isStatusOK());
            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);
            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);

        }
        catch (Exception exc){
            fail(exc.getMessage());
        }

    }


    @Test
    public void RejectInitialedRelationTest(){
        try{
            String patientContractAddress = accountController.getUserContractAddress(patient.getAddress()).send();
            UserContractApi patientContract = UserContract.load(patientContractAddress, patient.getAddress(), web3j);

            String doctorContractAddress = accountController.getUserContractAddress(doctor2.getAddress()).send();
            UserContractApi doctorContract = UserContract.load(doctorContractAddress, doctor2.getAddress(), web3j);

            RawTransaction initRawTransaction = patientContract.initRelationRawTransaction(doctor2.getAddress());
            TransactionReceipt transactionReceipt = patientContract
                    .executeInitRelation(patient.signTransaction(initRawTransaction))
                    .send();
            assertTrue(transactionReceipt.isStatusOK(), "Init relation failure");
            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
                    RelationStatus.REQUESTED);
            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
                    RelationStatus.INITIALED);

//            RawTransaction confirmRawTransaction = doctorContract.confirmRelationRawTransaction(patient.getAddress(), true);
//            TransactionReceipt confirmReceipt = doctorContract.executeConfirmRelation(doctor2.signTransaction(confirmRawTransaction)).send();
//            assertTrue(confirmReceipt.isStatusOK());
//
//            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
//                    RelationStatus.ACTIVE);
//            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
//                    RelationStatus.ACTIVE);

            RawTransaction rejectRawTransaction = patientContract.rejectRelationRawTransaction(doctor2.getAddress());
            TransactionReceipt rejectReceipt = patientContract
                    .executeRejectRelation(patient.signTransaction(rejectRawTransaction))
                    .send();
            assertTrue(rejectReceipt.isStatusOK());
            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);
            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);

        }
        catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    @Test
    public void RejectRequestedRelationTest() {
        try {
            String patientContractAddress = accountController.getUserContractAddress(patient.getAddress()).send();
            UserContractApi patientContract = UserContract.load(patientContractAddress, patient.getAddress(), web3j);

            String doctorContractAddress = accountController.getUserContractAddress(doctor2.getAddress()).send();
            UserContractApi doctorContract = UserContract.load(doctorContractAddress, doctor2.getAddress(), web3j);

            RawTransaction initRawTransaction = patientContract.initRelationRawTransaction(doctor2.getAddress());
            TransactionReceipt transactionReceipt = patientContract
                    .executeInitRelation(patient.signTransaction(initRawTransaction))
                    .send();
            assertTrue(transactionReceipt.isStatusOK(), "Init relation failure");
            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
                    RelationStatus.REQUESTED);
            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
                    RelationStatus.INITIALED);

//            RawTransaction confirmRawTransaction = doctorContract.confirmRelationRawTransaction(patient.getAddress(), true);
//            TransactionReceipt confirmReceipt = doctorContract.executeConfirmRelation(doctor2.signTransaction(confirmRawTransaction)).send();
//            assertTrue(confirmReceipt.isStatusOK());
//
//            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
//                    RelationStatus.ACTIVE);
//            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
//                    RelationStatus.ACTIVE);

            RawTransaction rejectRawTransaction = doctorContract.rejectRelationRawTransaction(patient.getAddress());
            TransactionReceipt rejectReceipt = doctorContract
                    .executeRejectRelation(doctor2.signTransaction(rejectRawTransaction))
                    .send();
            assertTrue(rejectReceipt.isStatusOK());
            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);
            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);

        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }


    @Test
    public void RejectInactiveRelationTest(){
        try{
            String patientContractAddress = accountController.getUserContractAddress(patient.getAddress()).send();
            UserContractApi patientContract = UserContract.load(patientContractAddress, patient.getAddress(), web3j);

            String doctorContractAddress = accountController.getUserContractAddress(doctor2.getAddress()).send();
            UserContractApi doctorContract = UserContract.load(doctorContractAddress, doctor2.getAddress(), web3j);

            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);
            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);



            RawTransaction rejectRawTransaction = patientContract.rejectRelationRawTransaction(doctor2.getAddress());
            TransactionReceipt rejectReceipt = patientContract
                    .executeRejectRelation(patient.signTransaction(rejectRawTransaction))
                    .send();
            assertTrue(rejectReceipt.isStatusOK());
            assertEquals(RelationStatus.values()[doctorContract.getRelationStatus(patient.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);
            assertEquals(RelationStatus.values()[patientContract.getRelationStatus(doctor2.getAddress()).send().intValue()],
                    RelationStatus.INACTIVE);

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
