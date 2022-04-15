//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "./UserContract.sol";
import "./PatientContract.sol";
import "../RegistryContract.sol";
import "./Relations/RelationsContract.sol";
import "../Account/IAccountController.sol";


contract DoctorContract is UserContract{

    constructor(address _owner, address _registryContractAddress) UserContract(_owner, _registryContractAddress) {
    } 

    modifier isPatient(address user){
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        require(accountController.getUserClass(user) == UserClass.PATIENT, "User must be a patient");
        _;
    }

    modifier isProvider(){
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        require(accountController.getUserClass(tx.origin) == UserClass.PROVIDER, "to add a record the user must be a provider");
        require(address(accountController.getUserContractAddress(tx.origin)) == msg.sender, "The provider can only add an record through his UserContract");
        _;
    }

    // function whoIsOwner() external view returns(address){
    //     return owner;
    // }

    // function addRecord(
    //     address patientAddress,
    //     bytes32 patientSmk,
    //     bytes32 patientEncodedHash,
    //     bytes32 doctorSmk,
    //     bytes32 doctorEncodedHash,
    //     address providerAddress,
    //     bytes32 providerSmk,
    //     bytes32 providerEncodedHash
    // ) external view returns(bool){
    //     return true;
    // }

    // function addRecord(
    //     address patientAddress,
    //     PermissionsContract.RecordMetadata calldata patientMetadata,
    //     PermissionsContract.RecordMetadata calldata doctorMetadata,
    //     address providerAddress,
    //     PermissionsContract.RecordMetadata calldata providerMetadata
    // ) external {
    //     return;
    // }

   function addRecord(
       address patientAddress,
       PermissionsContract.RecordMetadata memory patientMetadata,
       PermissionsContract.RecordMetadata memory doctorMetadata,
       address providerAddress,
       PermissionsContract.RecordMetadata memory providerMetadata
   ) external isPatient(patientAddress) hasRelation(patientAddress) {
       
       IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
       PatientContract(accountController.getUserContractAddress(patientAddress)).addRecord(patientMetadata, doctorMetadata, providerAddress, providerMetadata);

       //addRecordInfo(address(permissions), patientAddress);
   }
}