//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "../UserContract.sol";
import "../../RegistryContract.sol";
import "../../Account/IAccountController.sol";

contract RelationsContract{
    address userContractAddress;

    enum RelationStatus{INACTIVE, ACTIVE, REQUESTED, INITIALED}

    struct RelationInfo{
        address user;
        RelationStatus status;
        RelationsContract relationsContract;
    }

    mapping (address=>uint256) initIndex;
    address[] initialed;

    mapping (address => uint256) requestIndex;
    address[] requested;

    RelationInfo[] relations;
    mapping (address => uint256) indexOf;

    event requestAdded(address user);
    event newRelation(address user);
    event relationStatusChanged(address user, RelationStatus oldStatus, RelationStatus newStatus);

    constructor(){
        userContractAddress = msg.sender;
    }


    modifier userContractOnly() {
        require(msg.sender == userContractAddress, "The sender can only be the parent UserContract");
        _;
    }

    modifier wasInitBySender(){
        uint256 index = indexOf[tx.origin];
        require(index != 0 && relations[index - 1].status == RelationStatus.INITIALED && 
            address(relations[index - 1].relationsContract) == msg.sender &&
            tx.origin == relations[index - 1].user, "Incorrect relation confirmation");
        _;
    }

    modifier registeredOnly(){
        require(UserContract(userContractAddress).registryContract().isAuthorized(tx.origin), "The sender must be registered");
        _;
    }

    modifier senderIsRelationContract(){
        RegistryContract registryContract = UserContract(userContractAddress).registryContract();
        address accountController = registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT());
        if (accountController != address(0)){
            require(address(UserContract(IAccountController(accountController).getUserContractAddress(tx.origin)).relationsContract()) == msg.sender, 
                                        "Only a PermissionContract can initiate a relation");
        }
        _;
    }

    function getRelationRequests() userContractOnly external view returns(address[] memory){
        return requested;
    }

    function getInitialedRelations() userContractOnly external view returns(address[] memory){
        return initialed;
    }

    function initRelation(address user, RelationsContract relationContract) external userContractOnly{
        //uint256 index = indexOf[user];
        RelationStatus status = getStatus(user);
        if (status == RelationStatus.INITIALED || status == RelationStatus.ACTIVE)
            return;
        if ( status == RelationStatus.REQUESTED){
            processRequest(user, true);
        }
        else {
            relationContract.requestRelation();
            addRelationItem(user, RelationStatus.INITIALED, relationContract);

            initialed.push(user);
            initIndex[user] = initialed.length;
        }
        
    }

    //Process requested relation (user confirm)
    function processRequest(address user, bool confirm) public userContractOnly(){
        RelationStatus status = getStatus(user);
        if (status == RelationStatus.ACTIVE)
            return;
        require(status == RelationStatus.REQUESTED, "The request cannot be processed because it doesn't exist");
        
        RelationInfo storage relation = relations[indexOf[user] - 1];
        relation.relationsContract.confirmRelation(confirm);
        if (confirm){
            relation.status = RelationStatus.ACTIVE;
        }
        else {
            deleteRelation(user);
        }
        deleteFrom(user, requestIndex, requested);
    }

    function rejectRelation(address user) public userContractOnly {

        RelationStatus status = getStatus(user);
        if (status == RelationStatus.INACTIVE)
            return;
        if (status == RelationStatus.REQUESTED){
            processRequest(user, false);
            return;
        }
        relations[indexOf[user] - 1].relationsContract.rejectRelation();
        if (status == RelationStatus.INITIALED){
            deleteFrom(user, initIndex, initialed);
        }
        deleteRelation(user);
            
        
    }

    //external call from another RelationContract
    function rejectRelation() external registeredOnly senderIsRelationContract{
        address user = tx.origin;
        RelationStatus status = getStatus(user);
        require(status == RelationStatus.ACTIVE || status == RelationStatus.REQUESTED);
        if (status == RelationStatus.REQUESTED){
            deleteFrom(user, requestIndex, requested);
        }
        deleteRelation(user);
    }

    //external call from another RelationContract
    function confirmRelation(bool confirm) external wasInitBySender senderIsRelationContract{
        address user = tx.origin;
        uint256 index = indexOf[user];

        if (confirm){
            relations[index - 1].status = RelationStatus.ACTIVE;
            emit newRelation(user);
        }
        else {
            deleteRelation(user);                    
        }

        deleteFrom(user, initIndex, initialed);
    }


    //external call from another RelationContract
    function requestRelation() external registeredOnly senderIsRelationContract{
        address user = tx.origin;
        addRelationItem(user, RelationStatus.REQUESTED, RelationsContract(msg.sender));
        requested.push(user);
        requestIndex[user] = requested.length;
        emit requestAdded(user);
    }

    function getStatus(address user) public view returns(RelationStatus){
        if (indexOf[user] != 0)
            return relations[indexOf[user] - 1].status;
        return RelationStatus.INACTIVE;
    }

    function getAllRelations() external view returns(bytes[] memory){
        bytes[] memory res = new bytes[](relations.length);
        for (uint256 i = 0; i < relations.length; ++i){
            RelationInfo storage relation = relations[i];
            res[i] = abi.encodePacked(relation.user, uint256(relation.status));
        }
        return res;
    }

    function addRelationItem(address user, RelationStatus status, RelationsContract relationContract) internal {
        require(!exists(user), "Relation already exists");
        relations.push(RelationInfo(user, status, relationContract));
        indexOf[user] = relations.length;
    }

    function deleteFrom(address user, mapping(address => uint256) storage arrIndexOf, address[] storage arr) internal{ 
        uint256 index = arrIndexOf[user];
        assert(index != 0);

        if (index != arr.length){
            address lastAdddress = arr[arr.length - 1];
            arrIndexOf[lastAdddress] = index;
            arr[index - 1] = lastAdddress;
        }

        arr.pop();
        arrIndexOf[user] = 0;
    }

    function deleteRelation(address user) internal {
        uint256 index = indexOf[user];
        if (index == 0)
            return;
        if (index != relations.length){
            RelationInfo storage info = relations[relations.length - 1];
            indexOf[info.user] = index;
            relations[index - 1] = info;
        }

        relations.pop();
        indexOf[user] = 0;   
    }
  
    function exists(address user) internal view returns(bool){
        return indexOf[user] != 0;
    }
}