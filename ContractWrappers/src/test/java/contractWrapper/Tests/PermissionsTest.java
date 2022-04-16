package contractWrapper.Tests;

import contractWrapper.util.Options;
import healthyLife.contractWrappers.RegistryContract;
import healthyLife.contractWrappers.base.RawContract;
import healthyLife.contractWrappers.generated.AccountController;
import healthyLife.contractWrappers.generated.UserContractFactory;
import healthyLife.contractWrappers.user.DoctorContract;
import healthyLife.contractWrappers.user.UserContract;
import healthyLife.serverApi.util.Account;
import healthyLife.serverApi.models.permission.PermissionInfo;
import healthyLife.serverApi.models.permission.PermissionLevel;
import healthyLife.serverApi.models.permission.RecordMetadata;
import healthyLife.serverApi.models.permission.RecordModel;
import healthyLife.serverApi.wrappers.AccountControllerApi;
import healthyLife.serverApi.wrappers.user.DoctorContractApi;
import healthyLife.serverApi.wrappers.user.UserContractApi;
import org.junit.jupiter.api.*;
import org.web3j.EVMTest;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Numeric;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@EVMTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PermissionsTest {
    static final String providerPrivateKey = "c82aa158c161b6c4fe7c9658a09a245ed069ec79ec30dfbbb8bd199c3f1e2d45";
    static final String doctorPrivateKey = "59ddcb659507b6a247b4e6e4602728a4987c9c39e66e44182c4b334212d67dbc";
    static final String doctor2PrivateKey = "fef55a915f9e2261bd8498d9a91e5df99eea44296a87a9b7c966bdd74284b56f";
    static final String patientPrivateKey = "343ac984ce60d36fcbf800e1af3a7d6b5a0242d156d6ab9edfaac3cbac133c38";

    static Account provider;
    static Account doctor;
    static Account doctor2;
    static Account patient;

    private static Web3j web3j;

    static String registryContractAddress;
    static AccountControllerApi accountController;

    @BeforeAll
    public static void initValue() throws Exception {
        web3j = Web3j.build(new HttpService(Options.URL));

        provider = new Account(providerPrivateKey, web3j);
        doctor = new Account(doctorPrivateKey, web3j);
        doctor2 = new Account(doctor2PrivateKey, web3j);
        patient = new Account(patientPrivateKey, web3j);

        registryContractAddress = healthyLife.contractWrappers.generated.RegistryContract
                .deploy(web3j,
                        provider.credentials,
                        new StaticGasProvider(RawContract.GAS_PRICE, RawContract.GAS_LIMIT))
                .send()
                .getContractAddress();

        RegistryContract registryContract = RegistryContract.load(registryContractAddress, provider.getAddress(), web3j);

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
        if (!transactionReceipt.isStatusOK()) {
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }

        RawTransaction registryRawTransaction = accountController.registryPatientRawTransaction(patient.getAddress());
        transactionReceipt = accountController
                .executeRegistryPatient(provider.signTransaction(registryRawTransaction))
                .send();
        if (!transactionReceipt.isStatusOK()) {
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }

        registryRawTransaction = accountController.registryDoctorRawTransaction(doctor.getAddress());
        transactionReceipt = accountController
                .executeRegistryDoctor(provider.signTransaction(registryRawTransaction))
                .send();
        if (!transactionReceipt.isStatusOK()) {
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }

        registryRawTransaction = accountController.registryDoctorRawTransaction(doctor2.getAddress());
        transactionReceipt = accountController
                .executeRegistryDoctor(provider.signTransaction(registryRawTransaction))
                .send();
        if (!transactionReceipt.isStatusOK()) {
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }

        registryRawTransaction = accountController.registryProviderRawTransaction(provider.getAddress());
        transactionReceipt = accountController
                .executeRegistryProvider(provider.signTransaction(registryRawTransaction))
                .send();
        if (!transactionReceipt.isStatusOK()) {
            throw new TransactionException(transactionReceipt.getRevertReason(), transactionReceipt);
        }
    }

    @Test
    @Order(1)
    public void addRecordFailure() {
        try {
            DoctorContractApi doctorContract = DoctorContract.load(
                    accountController.getUserContractAddress(doctor.getAddress()).send(),
                    doctor.getAddress(),
                    web3j);

            RecordMetadata testRecordMetadata = new RecordMetadata(
                    Arrays.copyOf("patientTestSmk".getBytes(StandardCharsets.UTF_8), 32),
                    Arrays.copyOf("patientTestEncodedHash".getBytes(StandardCharsets.UTF_8), 32));
            RawTransaction addRecordRawTransaction = doctorContract
                    .addRecordRawTransaction(
                            patient.getAddress(),
                            testRecordMetadata,
                            testRecordMetadata,
                            provider.getAddress(),
                            testRecordMetadata
                    );
            TransactionReceipt addRecordReceipt = doctorContract
                    .executeAddRecord(doctor.signTransaction(addRecordRawTransaction))
                    .send();
            String reason = addRecordReceipt.isStatusOK() ? "" :
                    new String(Numeric.hexStringToByteArray(Numeric.cleanHexPrefix(addRecordReceipt.getRevertReason())));
            assertFalse(addRecordReceipt.isStatusOK());
            System.out.println(new String(Numeric.hexStringToByteArray(addRecordReceipt.getRevertReason())));

        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }

    @Test
    @Order(2)
    public void addRecordSuccess() {
        try {
            DoctorContractApi doctorContract = DoctorContract.load(
                    accountController.getUserContractAddress(doctor.getAddress()).send(),
                    doctor.getAddress(),
                    web3j);
            UserContractApi patientContract = UserContract.load(
                    accountController.getUserContractAddress(patient.getAddress()).send(),
                    patient.getAddress(),
                    web3j);
            UserContractApi providerContract = UserContract.load(
                    accountController.getUserContractAddress(provider.getAddress()).send(),
                    provider.getAddress(),
                    web3j);

            TransactionReceipt initReceipt = doctorContract.executeInitRelation(doctor.signTransaction(
                    doctorContract.initRelationRawTransaction(patient.getAddress())
            ))
                    .send();
            assertTrue(initReceipt.isStatusOK(), "Init relation failure");

            TransactionReceipt confirmReceipt = patientContract.executeConfirmRelation(
                    patient.signTransaction(
                            patientContract.confirmRelationRawTransaction(doctor.getAddress(), true)
                    )
            )
                    .send();
            assertTrue(confirmReceipt.isStatusOK(), "Confirm relation failure");

            RecordMetadata patientRecordMetadata = new RecordMetadata(
                    Arrays.copyOf("patientTestSmk".getBytes(StandardCharsets.UTF_8), 32),
                    Arrays.copyOf("patientTestEncodedHash".getBytes(StandardCharsets.UTF_8), 32));

            RecordMetadata doctorRecordMetadata = new RecordMetadata(
                    Arrays.copyOf("doctorTestSmk".getBytes(StandardCharsets.UTF_8), 32),
                    Arrays.copyOf("doctorTestEncodedHash".getBytes(StandardCharsets.UTF_8), 32));

            RecordMetadata providerRecordMetadata = new RecordMetadata(
                    Arrays.copyOf("providerTestSmk".getBytes(StandardCharsets.UTF_8), 32),
                    Arrays.copyOf("providerTestEncodedHash".getBytes(StandardCharsets.UTF_8), 32));

            RawTransaction addRecordRawTransaction = doctorContract
                    .addRecordRawTransaction(
                            patient.getAddress(),
                            patientRecordMetadata,
                            doctorRecordMetadata,
                            provider.getAddress(),
                            providerRecordMetadata
                    );

            TransactionReceipt addRecordReceipt = doctorContract
                    .executeAddRecord(doctor.signTransaction(addRecordRawTransaction))
                    .send();
            String reason = addRecordReceipt.isStatusOK() ? "" :
                    new String(Numeric.hexStringToByteArray(Numeric.cleanHexPrefix(addRecordReceipt.getRevertReason())));
            assertTrue(addRecordReceipt.isStatusOK(), reason);

            List<RecordModel> records = patientContract.getAllRecords().send();
            assertEquals(1, records.size());
            assertEquals(patientRecordMetadata, records.get(0).permissionInfo.metadata);

            records = doctorContract.getAllRecords().send();
            assertEquals(1, records.size());
            assertEquals(doctorRecordMetadata, records.get(0).permissionInfo.metadata);

            records = providerContract.getAllRecords().send();
            assertEquals(1, records.size());
            assertEquals(providerRecordMetadata, records.get(0).permissionInfo.metadata);


        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }

    @Test
    @Order(3)
    public void addPermissionReadOnlySender() {
        try {
            DoctorContractApi doctorContract = DoctorContract.load(
                    accountController.getUserContractAddress(doctor.getAddress()).send(),
                    doctor.getAddress(),
                    web3j);

            List<RecordModel> records = doctorContract.getAllRecords().send();
            assertTrue(records.size() > 0);
            String permissionId = records.get(0).permissionId;
            //Method check
            PermissionInfo permissionInfo = doctorContract.getPermissionInfo(permissionId).send();
            assertEquals(records.get(0).permissionInfo, permissionInfo);
            assertEquals(PermissionLevel.READ.toBigInteger(), permissionInfo.level);
            for (PermissionLevel level : PermissionLevel.values()) {
                TransactionReceipt addPermissionReceipt = doctorContract.executeAddPermission(doctor.signTransaction(
                        doctorContract.addPermissionRawTransaction(permissionId,
                                doctor2.getAddress(),
                                level.toBigInteger(),
                                permissionInfo.metadata)))
                        .send();
                String reason = new String(Numeric.hexStringToByteArray(addPermissionReceipt.getRevertReason()));
                assertFalse(addPermissionReceipt.isStatusOK());
            }
        } catch (Exception exc) {
            fail(exc);
        }
    }

    @Test
    @Order(4)
    public void addPermissionCanShareSender() {
        try {
            UserContractApi providerContract = UserContract.load(
                    accountController.getUserContractAddress(provider.getAddress()).send(),
                    provider.getAddress(),
                    web3j);

            List<RecordModel> records = providerContract.getAllRecords().send();
            assertTrue(records.size() > 0);
            String permissionId = records.get(0).permissionId;
            //Method check
            PermissionInfo permissionInfo = providerContract.getPermissionInfo(permissionId).send();
            assertEquals(records.get(0).permissionInfo, permissionInfo);
            assertEquals(PermissionLevel.SHARE.toBigInteger(), permissionInfo.level);

            TransactionReceipt addPermissionReceipt = providerContract.executeAddPermission(provider.signTransaction(
                    providerContract.addPermissionRawTransaction(permissionId,
                            doctor2.getAddress(),
                            PermissionLevel.OWNER.toBigInteger(),
                            permissionInfo.metadata)))
                    .send();
            String reason = new String(Numeric.hexStringToByteArray(addPermissionReceipt.getRevertReason()));
            assertFalse(addPermissionReceipt.isStatusOK());

            addPermissionReceipt = providerContract.executeAddPermission(provider.signTransaction(
                    providerContract.addPermissionRawTransaction(permissionId,
                            doctor2.getAddress(),
                            PermissionLevel.SHARE.toBigInteger(),
                            permissionInfo.metadata)))
                    .send();
            reason = new String(Numeric.hexStringToByteArray(addPermissionReceipt.getRevertReason()));
            assertFalse(addPermissionReceipt.isStatusOK());

            addPermissionReceipt = providerContract.executeAddPermission(provider.signTransaction(
                    providerContract.addPermissionRawTransaction(permissionId,
                            doctor2.getAddress(),
                            PermissionLevel.READ.toBigInteger(),
                            permissionInfo.metadata)))
                    .send();

            assertTrue(addPermissionReceipt.isStatusOK());

            List<PermissionInfo> permissionsInfo = providerContract.getAllPermissionsInfo(permissionId).send();
            assertEquals(4, permissionsInfo.size());
            List<String> permissionAddresses = permissionsInfo.stream().map(item -> item.user).collect(Collectors.toList());
            List<String> addresses = new ArrayList<String>();
            addresses.add(patient.getAddress());
            addresses.add(doctor.getAddress());
            addresses.add(doctor2.getAddress());
            addresses.add(provider.getAddress());
            for (String address : addresses) {
                assertTrue(permissionAddresses.contains(address));
            }
        } catch (Exception exc) {
            fail(exc);
        }
    }


    @Test
    @Order(5)
    public void EditPermissionTest() {
        try {
            DoctorContractApi doctorContract = DoctorContract.load(
                    accountController.getUserContractAddress(doctor.getAddress()).send(),
                    doctor.getAddress(),
                    web3j);
            UserContractApi patientContract = UserContract.load(
                    accountController.getUserContractAddress(patient.getAddress()).send(),
                    patient.getAddress(),
                    web3j);
            UserContractApi providerContract = UserContract.load(
                    accountController.getUserContractAddress(provider.getAddress()).send(),
                    provider.getAddress(),
                    web3j);

            List<RecordModel> records = patientContract.getAllRecords().send();
            assertEquals(1, records.size());
            String permissionId = records.get(0).permissionId;

            TransactionReceipt editPermissionReceipt = doctorContract.executeEditPermission(
                    doctor.signTransaction(doctorContract
                            .editPermissionRawTransaction(permissionId,
                                    doctor2.getAddress(),
                                    PermissionLevel.SHARE.toBigInteger())
                    ))
                    .send();
            assertFalse(editPermissionReceipt.isStatusOK());

            editPermissionReceipt = providerContract.executeEditPermission(
                    provider.signTransaction(providerContract
                            .editPermissionRawTransaction(permissionId,
                                    doctor2.getAddress(),
                                    PermissionLevel.SHARE.toBigInteger())
                    ))
                    .send();
            assertFalse(editPermissionReceipt.isStatusOK());

            editPermissionReceipt = patientContract.executeEditPermission(
                    patient.signTransaction(patientContract
                            .editPermissionRawTransaction(permissionId,
                                    doctor2.getAddress(),
                                    PermissionLevel.SHARE.toBigInteger())
                    ))
                    .send();
            assertTrue(editPermissionReceipt.isStatusOK());

            PermissionInfo info = patientContract.getPermissionInfo(permissionId, doctor2.getAddress()).send();
            assertEquals(PermissionLevel.SHARE.toBigInteger(), info.level);
            assertEquals(patient.getAddress(), info.permitter);

        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }

    @Test
    @Order(6)
    public void RemovePermissionTest() {
        try {
            DoctorContractApi doctorContract = DoctorContract.load(
                    accountController.getUserContractAddress(doctor.getAddress()).send(),
                    doctor.getAddress(),
                    web3j);
            UserContractApi patientContract = UserContract.load(
                    accountController.getUserContractAddress(patient.getAddress()).send(),
                    patient.getAddress(),
                    web3j);
            UserContractApi providerContract = UserContract.load(
                    accountController.getUserContractAddress(provider.getAddress()).send(),
                    provider.getAddress(),
                    web3j);

            List<RecordModel> records = patientContract.getAllRecords().send();
            assertEquals(1, records.size());
            String permissionId = records.get(0).permissionId;
            PermissionInfo info = patientContract.getPermissionInfo(permissionId, doctor2.getAddress()).send();
            assertEquals(patient.getAddress(), info.permitter);
            List<PermissionInfo> permissions = patientContract.getAllPermissionsInfo(permissionId).send();

            //Удалить может владелец или тот, кто дал доступ (сейчас владелец)


            TransactionReceipt removeTransaction = doctorContract.executeRemovePermission(
                    doctor.signTransaction(
                            doctorContract.removePermissionRawTransaction(permissionId, doctor2.getAddress())))
                    .send();
            assertFalse(removeTransaction.isStatusOK());

            removeTransaction = providerContract.executeRemovePermission(
                    provider.signTransaction(
                            providerContract.removePermissionRawTransaction(permissionId, doctor2.getAddress())))
                    .send();
            assertFalse(removeTransaction.isStatusOK());

            removeTransaction = patientContract.executeRemovePermission(
                    patient.signTransaction(
                            patientContract.removePermissionRawTransaction(permissionId, doctor2.getAddress())))
                    .send();
            assertTrue(removeTransaction.isStatusOK());

            // Удалит не владелец, но тот кто предоставил

            TransactionReceipt addPermissionReceipt = providerContract.executeAddPermission(
                    provider.signTransaction(
                            providerContract.addPermissionRawTransaction(permissionId,
                                    doctor2.getAddress(),
                                    PermissionLevel.READ.toBigInteger(),
                                    new RecordMetadata(Bytes32.DEFAULT, Bytes32.DEFAULT))))
                    .send();
            String reason = addPermissionReceipt.isStatusOK() ? "" :
                    new String(Numeric.hexStringToByteArray(addPermissionReceipt.getRevertReason()));
            assertTrue(addPermissionReceipt.isStatusOK(), reason);
            removeTransaction = providerContract.executeRemovePermission(
                    provider.signTransaction(
                            providerContract.removePermissionRawTransaction(permissionId, doctor2.getAddress())))
                    .send();
            reason = addPermissionReceipt.isStatusOK() ? "" :
                    new String(Numeric.hexStringToByteArray(addPermissionReceipt.getRevertReason()));
            assertTrue(removeTransaction.isStatusOK(), reason);

            //Удалит владелец, но не он предоставил доступ
            addPermissionReceipt = providerContract.executeAddPermission(
                    provider.signTransaction(
                            providerContract.addPermissionRawTransaction(permissionId,
                                    doctor2.getAddress(),
                                    PermissionLevel.READ.toBigInteger(),
                                    new RecordMetadata(Bytes32.DEFAULT, Bytes32.DEFAULT))))
                    .send();
            assertTrue(addPermissionReceipt.isStatusOK());
            removeTransaction = patientContract.executeRemovePermission(
                    patient.signTransaction(
                            patientContract.removePermissionRawTransaction(permissionId, doctor2.getAddress())))
                    .send();
            assertTrue(removeTransaction.isStatusOK());





        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }

}
