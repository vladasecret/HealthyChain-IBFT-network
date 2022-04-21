//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "./UserContract.sol";
import "../RegistryContract.sol";
import "./Relations/RelationsContract.sol";
import "../Account/IAccountController.sol";

contract PatientContract is UserContract{

    constructor(address _owner, address _registryContractAddress) UserContract(_owner, _registryContractAddress) {
    } 

    modifier isDoctor(){
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        require(accountController.getUserClass(tx.origin) == UserClass.DOCTOR, "to add a record the user must be a doctor");
        require(accountController.getUserContractAddress(tx.origin) == msg.sender, "The doctor can only add an record through his UserContract");
        _;
    }

    modifier isProvider(){
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        require(accountController.getUserClass(tx.origin) == UserClass.PROVIDER, "to add a record the user must be a provider");
        require(accountController.getUserContractAddress(tx.origin) == msg.sender, "The provider can only add an record through his UserContract");
        _;
    }
    
    function addRecord(
        bytes[] calldata patientData, 
        bytes[] calldata doctorData,
        address providerAddress, 
        bytes[] calldata providerData
    ) external hasRelation isDoctor returns (address){
        PermissionsContract permissions = new PermissionsContract(owner, patientData[0], patientData[1]);
        permissions.addPermission(tx.origin, PermissionsContract.PermissionLevel.READ, doctorData[0], doctorData[1]);
        permissions.addPermission(providerAddress,  PermissionsContract.PermissionLevel.SHARE, providerData[0], providerData[1]);  
        
        addRecordInfo(address(permissions), tx.origin);
        return address(permissions);
    }

    function addRecord(
        bytes[] calldata patientData,  
        bytes[] calldata providerData
    ) external hasRelation isDoctor returns(address){
        PermissionsContract permissions = new PermissionsContract(owner, patientData[0], patientData[1]);
        permissions.addPermission(tx.origin,  PermissionsContract.PermissionLevel.SHARE, providerData[0], providerData[1]);  
        
        addRecordInfo(address(permissions), tx.origin);
        return address(permissions);
    } 
}