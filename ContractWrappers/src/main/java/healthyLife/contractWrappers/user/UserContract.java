package healthyLife.contractWrappers.user;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;


import healthyLife.serverApi.models.permission.PermissionInfo;
import healthyLife.serverApi.models.permission.RecordModel;
import healthyLife.serverApi.models.permission.RecordMetadata;
import healthyLife.serverApi.models.relation.RelationInfoModel;
import healthyLife.serverApi.models.relation.RelationStatus;
import healthyLife.serverApi.wrappers.user.UserContractApi;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint8;
import healthyLife.contractWrappers.base.RawContract;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.exceptions.ContractCallException;

public class UserContract extends RawContract implements UserContractApi {

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_HASACTIVERELATION = "hasActiveRelation";

    public static final String FUNC_ADDPERMISSION = "addPermission";

    public static final String FUNC_GETRELATIONSTATUS = "getRelationStatus";

    public static final String FUNC_CONFIRMRELATION = "confirmRelation";

    public static final String FUNC_EDITPERMISSION = "editPermission";

    public static final String FUNC_GETALLRECORDSMETADATA = "getAllRecordsMetadata";

    public static final String FUNC_GETALLRELATIONS = "getAllRelations";

    public static final String FUNC_GETASSOCIATEDUSERS = "getAssociatedUsers";

    public static final String FUNC_getPermissionInfo = "getPermissionInfo";

    public static final String FUNC_GETALLPERMISSIONSINFO = "getAllPermissionsInfo";

    public static final String FUNC_GETALLRECORDS = "getAllRecords";

    public static final String FUNC_GETINITIALEDRELATIONS = "getInitialedRelations";

    public static final String FUNC_GETRECORDMETADATA = "getRecordMetadata";

    public static final String FUNC_GETRECORDSMETADATABYUSER = "getRecordsMetadataByUser";

    public static final String FUNC_GETRELATIONREQUESTS = "getRelationRequests";

    public static final String FUNC_INITRELATION = "initRelation";

    public static final String FUNC_REGISTRYCONTRACT = "registryContract";

    public static final String FUNC_GETRECORDSBYUSER = "getRecordsByUser";

    public static final String FUNC_REJECTRELATION = "rejectRelation";

    public static final String FUNC_HASPERMISSION = "hasPermission";

    public static final String FUNC_RELATIONSCONTRACT = "relationsContract";

    public static final String FUNC_REMOVEPERMISSION = "removePermission";

    public static final String FUNC_REMOVEPERMISSIONINTERNAL = "removePermissionInternal";

    protected UserContract(String contractAddress, String senderAddress, Web3j web3j) {
        super(contractAddress, senderAddress, web3j);
    }

    public static UserContract load(String contractAddress, String senderAddress, Web3j web3j) {
        if (!hasCode(contractAddress, web3j)) {
            throw new ContractCallException("There is no smart contract at the specified address");
        }
        return new UserContract(contractAddress, senderAddress, web3j);
    }

    public RemoteFunctionCall<String> getOwner() {
        final Function function = new Function(FUNC_GETOWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> hasPermission(String permissionsAddress) {
        final Function function = new Function(FUNC_HASPERMISSION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, permissionsAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RawTransaction addPermissionRawTransaction(String permissionsId, String user, BigInteger level, RecordMetadata metadata) {
        final Function function = new Function(
                FUNC_ADDPERMISSION,
                Arrays.<Type>asList(new Address(160, permissionsId),
                        new Address(160, user),
                        new Uint8(level),
                        metadata),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }

    public RemoteCall<TransactionReceipt> executeAddPermission(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_ADDPERMISSION,
                Arrays.<Type>asList(Address.DEFAULT,
                        Address.DEFAULT,
                        Uint8.DEFAULT,
                        new RecordMetadata(Bytes32.DEFAULT, Bytes32.DEFAULT)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }


    public RemoteFunctionCall<PermissionInfo> getPermissionInfo(String permissionsId) {
        final Function function = new Function(FUNC_getPermissionInfo,
                Arrays.<Type>asList(new Address(160, permissionsId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<PermissionInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, PermissionInfo.class);
    }

    public RemoteFunctionCall<PermissionInfo> getPermissionInfo(String permissionsId, String user) {
        final Function function = new Function(FUNC_getPermissionInfo,
                Arrays.<Type>asList(new Address(160, permissionsId),
                        new Address(160, user)),
                Arrays.<TypeReference<?>>asList(new TypeReference<PermissionInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, PermissionInfo.class);
    }


    public RemoteFunctionCall<List<PermissionInfo>> getAllPermissionsInfo(String permissionsId) {
        final Function function = new Function(FUNC_GETALLPERMISSIONSINFO,
                Arrays.<Type>asList(new Address(160, permissionsId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<PermissionInfo>>() {}));
        return new RemoteFunctionCall<List<PermissionInfo>>(function,
                new Callable<List<PermissionInfo>>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RawTransaction confirmRelationRawTransaction(String user, Boolean confirmStatus) {
        final Function function = new Function(
                FUNC_CONFIRMRELATION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user),
                        new org.web3j.abi.datatypes.Bool(confirmStatus)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }


    public RemoteFunctionCall<Boolean> hasActiveRelation(String user) {
        final Function function = new Function(FUNC_HASACTIVERELATION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
                }));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }


    public RemoteFunctionCall<BigInteger> getRelationStatus(String user) {
        final Function function = new Function(FUNC_GETRELATIONSTATUS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> executeConfirmRelation(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_CONFIRMRELATION,
                Arrays.<Type>asList(Address.DEFAULT,
                        Bool.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }

    public RawTransaction editPermissionRawTransaction(String permissionsId, String user, BigInteger newLevel) {
        final Function function = new Function(
                FUNC_EDITPERMISSION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, permissionsId),
                        new org.web3j.abi.datatypes.Address(160, user),
                        new org.web3j.abi.datatypes.generated.Uint8(newLevel)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }

    public RemoteCall<TransactionReceipt> executeEditPermission(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_EDITPERMISSION,
                Arrays.<Type>asList(
                        Address.DEFAULT,
                        Address.DEFAULT,
                        Uint8.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }


    public RemoteFunctionCall<List<RecordModel>> getAllRecords() {
        final Function function = new Function(FUNC_GETALLRECORDS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<RecordDTO>>() {}));
        return new RemoteFunctionCall<List<RecordModel>>(function,
                new Callable<List<RecordModel>>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result)
                                .stream()
                                .map(obj -> {
                                    RecordDTO recordDTO = (RecordDTO)obj;
                                    return new RecordModel(recordDTO.permissionsContractAddress,
                                            recordDTO.associatedUser,
                                            new PermissionInfo(recordDTO.user,
                                                    recordDTO.level,
                                                    recordDTO.metadata,
                                                    recordDTO.permitter));
                                })
                                .collect(Collectors.toList());
                    }
                });
    }


    public RemoteFunctionCall<List<RelationInfoModel>> getAllRelations() {
        final Function function = new Function(FUNC_GETALLRELATIONS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<healthyLife.contractWrappers.user.UserContract.RelationInfo>>() {
                }));
        return new RemoteFunctionCall<List<RelationInfoModel>>(function,
                new Callable<List<RelationInfoModel>>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result).stream().map(item -> {
                            RelationInfo rel = (RelationInfo) item;
                            return new RelationInfoModel(rel.user, rel.status);
                        }).collect(Collectors.toList());
                    }
                });
    }



    public RemoteCall<List<String>> getInitialedRelations() {
        return new RemoteCall<List<String>>(
                new Callable<List<String>>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        return getAllRelations().send()
                                .stream()
                                .filter(item -> item.status == RelationStatus.INITIALED)
                                .map(item -> item.user)
                                .collect(Collectors.toList());
                    }
                });
    }

    public RemoteFunctionCall<RecordMetadata> getRecordMetadata(String permissionsAddress) {
        final Function function = new Function(FUNC_GETRECORDMETADATA,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, permissionsAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<RecordMetadata>() {
                }));
        return executeRemoteCallSingleValueReturn(function, RecordMetadata.class);
    }




    public RemoteCall<List<String>> getRelationRequests() {
        return new RemoteCall<List<String>>(
                new Callable<List<String>>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List<String> call() throws Exception {
                        return getAllRelations()
                                .send()
                                .stream()
                                .filter(item -> item.status == RelationStatus.REQUESTED)
                                .map(item -> item.user)
                                .collect(Collectors.toList());
                    }
                });
    }


    public RawTransaction initRelationRawTransaction(String user) {
        final Function function = new Function(
                FUNC_INITRELATION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }


    public RemoteCall<TransactionReceipt> executeInitRelation(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_INITRELATION,
                Arrays.<Type>asList(Address.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }


    public RawTransaction rejectRelationRawTransaction(String user) {
        final Function function = new Function(
                FUNC_REJECTRELATION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }


    public RemoteCall<TransactionReceipt> executeRejectRelation(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_REJECTRELATION,
                Arrays.<Type>asList(Address.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }

    public RawTransaction removePermissionRawTransaction(String permissionsId, String user) {
        final Function function = new Function(
                FUNC_REMOVEPERMISSION,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, permissionsId),
                        new org.web3j.abi.datatypes.Address(160, user)),
                Collections.<TypeReference<?>>emptyList());
        return createRawTransaction(function);
    }


    public RemoteCall<TransactionReceipt> executeRemovePermission(String hexTransaction) throws TransactionException {
        final Function function = new Function(
                FUNC_REMOVEPERMISSION,
                Arrays.<Type>asList(Address.DEFAULT,
                        Address.DEFAULT),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallSignedTransaction(hexTransaction, function);
    }



//    public static class RecordMetadata extends StaticStruct {
//        public byte[] smk;
//
//        public byte[] encodedHash;
//
//        public RecordMetadata(byte[] smk, byte[] encodedHash) {
//            super(new org.web3j.abi.datatypes.generated.Bytes32(smk),
//                    new org.web3j.abi.datatypes.generated.Bytes32(encodedHash));
//            this.smk = smk;
//            this.encodedHash = encodedHash;
//        }
//
//        public RecordMetadata(Bytes32 smk, Bytes32 encodedHash) {
//            super(smk, encodedHash);
//            this.smk = smk.getValue();
//            this.encodedHash = encodedHash.getValue();
//        }
//    }

//    public static class RecordInfo extends StaticStruct {
//        public String permissionsContractAddress;
//
//        public String associatedUser;
//
//        public RecordInfo(String permissionsContractAddress, String associatedUser) {
//            super(new org.web3j.abi.datatypes.Address(160, permissionsContractAddress),
//                    new org.web3j.abi.datatypes.Address(160, associatedUser));
//            this.permissionsContractAddress = permissionsContractAddress;
//            this.associatedUser = associatedUser;
//        }
//
//        public RecordInfo(Address permissionsContractAddress, Address associatedUser) {
//            super(permissionsContractAddress, associatedUser);
//            this.permissionsContractAddress = permissionsContractAddress.getValue();
//            this.associatedUser = associatedUser.getValue();
//        }
//    }

    public static class RelationInfo extends StaticStruct {
        public String user;

        public BigInteger status;

        public String relationsContract;

        public RelationInfo(String user, BigInteger status, String relationsContract) {
            super(new org.web3j.abi.datatypes.Address(160, user),
                    new org.web3j.abi.datatypes.generated.Uint8(status),
                    new org.web3j.abi.datatypes.Address(160, relationsContract));
            this.user = user;
            this.status = status;
            this.relationsContract = relationsContract;
        }

        public RelationInfo(Address user, Uint8 status, Address relationsContract) {
            super(user, status, relationsContract);
            this.user = user.getValue();
            this.status = status.getValue();
            this.relationsContract = relationsContract.getValue();
        }
    }

    public static class RecordDTO extends StaticStruct {
        public String permissionsContractAddress;

        public String associatedUser;

        public String user;

        public BigInteger level;

        public RecordMetadata metadata;

        public String permitter;

        public RecordDTO(String permissionsContractAddress,
                         String associatedUser,
                         String user,
                         BigInteger level,
                         RecordMetadata metadata,
                         String permitter) {
            super(new org.web3j.abi.datatypes.Address(160, permissionsContractAddress),
                    new org.web3j.abi.datatypes.Address(160, associatedUser),
                    new org.web3j.abi.datatypes.Address(160, user),
                    new org.web3j.abi.datatypes.generated.Uint8(level),
                    metadata,
                    new org.web3j.abi.datatypes.Address(160, permitter));
            this.permissionsContractAddress = permissionsContractAddress;
            this.associatedUser = associatedUser;
            this.user = user;
            this.level = level;
            this.metadata = metadata;
            this.permitter = permitter;
        }

        public RecordDTO(Address permissionsContractAddress,
                         Address associatedUser,
                         Address user,
                         Uint8 level,
                         RecordMetadata metadata,
                         Address permitter) {
            super(permissionsContractAddress, associatedUser, user, level, metadata, permitter);
            this.permissionsContractAddress = permissionsContractAddress.getValue();
            this.associatedUser = associatedUser.getValue();
            this.user = user.getValue();
            this.level = level.getValue();
            this.metadata = metadata;
            this.permitter = permitter.getValue();
        }
    }
}
