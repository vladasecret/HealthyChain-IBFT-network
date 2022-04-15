//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "../RegistryContract.sol";
import "./Relations/RelationsContract.sol";
import "./PermissionsContract.sol";

contract UserContract{

    struct RecordInfo{
        address permissionsContractAddress;
        address associatedUser;
    }

    struct RecordDTO {
        address permissionsContractAddress;
        address associatedUser;
        address user;
        PermissionsContract.PermissionLevel level;
        PermissionsContract.RecordMetadata metadata;
        address permitter;
    }
        
    address owner;

    RegistryContract public registryContract; 
    RelationsContract public relationsContract;

    mapping(address => uint256) indexById;
    RecordInfo[] records;

    modifier hasRelation(address user){
        require(relationsContract.getStatus(user) == RelationsContract.RelationStatus.ACTIVE,
            "The sender does not have an active relation with the recipient");
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

    function hasPermission(address permissionsAddress) external view returns(bool){
        assert(indexById[permissionsAddress] != 0 && !PermissionsContract(permissionsAddress).hasAccess(msg.sender, msg.sender));
        return indexById[permissionsAddress] != 0;
    }

    function hasActiveRelation(address user) external view returns(bool){
        return relationsContract.getStatus(user) == RelationsContract.RelationStatus.ACTIVE;
    }

    function getRelationStatus(address user) external view returns(RelationsContract.RelationStatus){
        return relationsContract.getStatus(user);
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

    function getAllRelations() onlyOwner external view returns(RelationsContract.RelationInfo[] memory){
        return relationsContract.getAllRelations();
    }

    function addPermission(address permissionsId, address user, PermissionsContract.PermissionLevel level, PermissionsContract.RecordMetadata memory metadata) external onlyOwner {
        require(exists(permissionsId), "PermissionsId does not exist");
        PermissionsContract permission = PermissionsContract(permissionsId);
        permission.addPermission(user, level, metadata);
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        UserContract(accountController.getUserContractAddress(user)).addPermissionInternal(owner, permissionsId);
    }

    function addPermissionInternal(address senderOwner, address permissionsAddress) external UserContractOnly(senderOwner){
        require(!exists(permissionsAddress), "PermissionsAddress already exists");
        addRecordInfo(permissionsAddress, PermissionsContract(permissionsAddress).dataOwner());
        
    }

    function getPermissionInfo(address permissionsId) onlyOwner external view returns(PermissionsContract.PermissionInfo memory){
        return getPermissionInfo(permissionsId, msg.sender);
    }

    function getPermissionInfo(address permissionsId, address user) onlyOwner public view returns(PermissionsContract.PermissionInfo memory){
        return PermissionsContract(permissionsId).getPermissionInfo(user);
    }

    function getAllPermissionsInfo(address permissionsId) onlyOwner external view returns(PermissionsContract.PermissionInfo[] memory){
        require(exists(permissionsId), "The specified id is not registered");
        return PermissionsContract(permissionsId).getPermissions();
    }

    function getPermittedUsers(address permissionsId) onlyOwner external view returns(PermissionsContract.UserPermission[] memory){
        require(exists(permissionsId), "The specified id is not registered");
        return PermissionsContract(permissionsId).getPermittedUsers();
    }

    function editPermission(address permissionsId, address user, PermissionsContract.PermissionLevel newLevel) external onlyOwner{
        require(exists(permissionsId), "PermissionId does not exist");
        PermissionsContract(permissionsId).editPermission(user, newLevel);
    }

    function removePermission(address permissionsId, address user) external onlyOwner{
        require(exists(permissionsId), "PermissionsId does not exist");
        PermissionsContract(permissionsId).removePermission(user);
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        UserContract(accountController.getUserContractAddress(user)).removePermissionInternal(permissionsId);

    }

    function removePermissionInternal(address permissionsAddress) external UserContractOnly(tx.origin){
        removeRecordInfo(permissionsAddress);      
    }

    function getRecordMetadata(address permissionsAddress) external view onlyOwner returns(PermissionsContract.RecordMetadata memory){
        require(exists(permissionsAddress), "permissions address does not exists");
        return PermissionsContract(permissionsAddress).getRecordMetadata(msg.sender);
    }

    function getAllRecords () external view onlyOwner returns(RecordDTO[] memory){
        RecordDTO[] memory res = new RecordDTO[](records.length);
        for (uint256 i = 0; i < records.length; ++i){
            PermissionsContract permContract = PermissionsContract(records[i].permissionsContractAddress);
            PermissionsContract.PermissionInfo memory permInfo = permContract.getPermissionInfo(msg.sender);
            res[i] = RecordDTO(records[i].permissionsContractAddress, records[i].associatedUser, permInfo.user, permInfo.level, permInfo.metadata, permInfo.permitter);
        }
        return res;
    }

    function exists(address permissionsAddress)internal view returns(bool){
        return indexById[permissionsAddress] != 0;
    }

    function addRecordInfo(address permissionsAddress, address associatedUser) internal {
        if (indexById[permissionsAddress] != 0){
            require(records[indexById[permissionsAddress] - 1].associatedUser == associatedUser,
                "PermissionsAddress already exists with another associated user");
            return;
        }
        records.push(RecordInfo(permissionsAddress, associatedUser));
        indexById[permissionsAddress] = records.length;
    }
    
    function removeRecordInfo(address permissionsId) internal {
        if (!exists(permissionsId))
            return;

        uint256 index = indexById[permissionsId];

        if (index != records.length){
            RecordInfo storage lastRecord = records[records.length - 1];
            records[index - 1] = lastRecord;
            indexById[lastRecord.permissionsContractAddress] = index;
        }

        records.pop();
        indexById[permissionsId] = 0;
    }
}