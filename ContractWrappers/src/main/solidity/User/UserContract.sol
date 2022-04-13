//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "../RegistryContract.sol";
import "./Relations/RelationsContract.sol";
import "./PermissionsContract.sol";

contract UserContract{

    struct RecordInfo{
        //contract address is ID
        address permissionsContractAddress;
        address associatedUser;
    }
        
    address owner;

    RegistryContract public registryContract; 
    RelationsContract public relationsContract;

    mapping(address => uint256) indexById;
    RecordInfo[] records;

    mapping(address => uint256) indexOfUser;
    address[] associatedUsers;
        
    //record index in recordsByUser;
    mapping(address => mapping(address => uint256)) recordIndexByUser;
    mapping(address => address[]) recordsByUser;

    modifier hasRelation(){
        require(relationsContract.getStatus(tx.origin) == RelationsContract.RelationStatus.ACTIVE);
        _;
    }

    modifier UserContractOnly(address user){
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        require(accountController.getUserContractAddress(user) == msg.sender, "The sender must be UserContract");
        _;
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "The sender must be the owner of the contract");
        _;
    }

    constructor(address _owner, address _registryContractAddress){
        owner = _owner;
        registryContract = RegistryContract(_registryContractAddress);
        relationsContract = new RelationsContract();
    }

    function getOwner() external view returns(address){
        return owner;
    }

    function hasActiveRelation(address user) external view returns(bool){
        return relationsContract.getStatus(user) == RelationsContract.RelationStatus.ACTIVE;
    }

    function initRelation(address user) onlyOwner external{
        require(registryContract.isAuthorized(user), "Unable to communicate with unauthorized user");
        require(registryContract.hasContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()), 
                "It is impossible to initiate a relation without a AccountController contract");
        
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        relationsContract.initRelation(user, UserContract(accountController.getUserContractAddress(user)).relationsContract());
    }

    function confirmRelation(address user, bool confirmStatus) onlyOwner external{
        relationsContract.processRequest(user, confirmStatus);
    }

    function rejectRelation(address user) onlyOwner external{
        relationsContract.rejectRelation(user);
    }

    function getRelationRequests() onlyOwner external view returns(address[] memory){
        return relationsContract.getRelationRequests();
    }

    function getInitialedRelations() onlyOwner external view returns(address[] memory){
        return relationsContract.getInitialedRelations();
    }

    function getAllRelations() onlyOwner external view returns(bytes[] memory){
        return relationsContract.getAllRelations();
    }

    function getAssociatedUsers() onlyOwner external view returns(address[] memory){
        return associatedUsers;
    }
    
    function addPermission(address permissionsId, address user, PermissionsContract.PermissionLevel level, bytes memory smk, bytes memory encodedHash) external onlyOwner {
        require(exists(permissionsId), "PermissionsId does not exist");
        PermissionsContract permission = PermissionsContract(permissionsId);
        permission.addPermission(user, level, smk, encodedHash);
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        UserContract(accountController.getUserContractAddress(user)).addPermissionInternal(permissionsId);
    }

    function addPermissionInternal(address permissionsAddress) external UserContractOnly(tx.origin){
        if (!exists(permissionsAddress)){
            addRecordInfo(permissionsAddress, PermissionsContract(permissionsAddress).dataOwner());
        }
    }

    function editPermission(address permissionsId, address user, PermissionsContract.PermissionLevel newLevel) external onlyOwner{
        require(exists(permissionsId));   
        PermissionsContract(permissionsId).editPermission(user, newLevel);
    }

    function removePermission(address permissionsId, address user) external onlyOwner{
        require(exists(permissionsId), "PermissionsId does not exist");
        PermissionsContract(permissionsId).removePermission(user);
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        UserContract(accountController.getUserContractAddress(user)).removePermissionInternal(permissionsId);

    }

    function removePermissionInternal(address permissionsAddress) external UserContractOnly(tx.origin){
        if (!exists(permissionsAddress)){
            removeRecordInfo(permissionsAddress);
        }
                    
    }

    function getRecordMetadata(address permissionsAddress) external view onlyOwner returns(bytes memory smk, bytes memory encodedHash){
        require(exists(permissionsAddress), "permissions address does not exists");
        //require(PermissionsContract(permissionsAddress).hasAccess(tx.origin, tx.origin), "User does not has pemissions");
        return PermissionsContract(permissionsAddress).getMetadata(msg.sender);
    }

    function getAllRecordsMetadata() external view onlyOwner returns(bytes[] memory metadata){
        bytes[] memory res = new bytes[](records.length);
        for (uint256 i = 0; i < records.length; ++i){
            PermissionsContract permContract = PermissionsContract(records[i].permissionsContractAddress);
            //if (permContract.hasAccess(msg.sender, msg.sender)){
                (bytes memory smk, bytes memory encodedHash) = permContract.getMetadata(msg.sender);
                res[i] = abi.encodePacked(records[i].permissionsContractAddress, smk, encodedHash);
            //}
        }
        return res;
    }

    function getRecordsMetadataByUser(address associatedUser) external view onlyOwner returns (bytes[] memory metadata){
        require(isAssociated(associatedUser), "User not associated");
        address[] storage permissionsArr = recordsByUser[associatedUser];
        bytes[] memory res = new bytes[](permissionsArr.length);
        for (uint256 i = 0; i < permissionsArr.length; ++i){
            if (PermissionsContract(permissionsArr[i]).hasAccess(msg.sender, msg.sender)){
                (bytes memory smk, bytes memory encodedHash) = PermissionsContract(permissionsArr[i]).getMetadata(msg.sender);
                res[i] = abi.encodePacked(permissionsArr[i], smk, encodedHash);
            }
        }
        return res;
    }

    function exists(address permissionsAddress)internal view returns(bool){
        return indexById[permissionsAddress] != 0;
    }

    function isAssociated(address user) internal view returns(bool){
        return indexOfUser[user] != 0;
    }

    function addRecordInfo(address permissionsAddress, address associatedUser) internal {
        records.push(RecordInfo(permissionsAddress, associatedUser));
        indexById[permissionsAddress] = records.length;

        recordsByUser[associatedUser].push(permissionsAddress);
        recordIndexByUser[associatedUser][permissionsAddress] = recordsByUser[associatedUser].length;

        if (!isAssociated(associatedUser)){
            associatedUsers.push(associatedUser);
            indexOfUser[associatedUser] = associatedUsers.length;
        }
    }
    
    function removeRecordInfo(address permissionsId) internal {
        if (!exists(permissionsId))
            return;

        uint256 index = indexById[permissionsId];
        address associatedUser = records[index - 1].associatedUser;

        if (index != records.length){
            RecordInfo storage lastRecord = records[records.length - 1];
            records[index - 1] = lastRecord;
            indexById[lastRecord.permissionsContractAddress] = index;
        }

        records.pop();
        indexById[permissionsId] = 0;
        
        removeFrom(permissionsId, recordIndexByUser[associatedUser], recordsByUser[associatedUser]);
        // index = recordIndexByUser[associatedUser][permissionsId];
        // assert(index != 0);
        // address[] storage arr =  recordsByUser[associatedUser];
        // if (index != arr.length){
        //     address lastAddress = arr[arr.length - 1];
        //     arr[index - 1] = lastAddress;
        //     recordIndexByUser[associatedUser][lastAddress] = index;
        // }

        // arr.pop();
        // recordIndexByUser[associatedUser][permissionsId] = 0;

        if (recordsByUser[associatedUser].length == 0){
            removeFrom(associatedUser, indexOfUser, associatedUsers);
        }
    }

    function removeFrom(address item, mapping(address => uint256) storage map, address[] storage arr) internal {
        uint256 index = map[item];
        require(index != 0, "Item does not exist");
        if (index != arr.length){
            address lastAddress = arr[arr.length - 1];
            arr[index - 1] = lastAddress;
            map[lastAddress] = index;
        }

        arr.pop();
        map[item] = 0;


    }
}