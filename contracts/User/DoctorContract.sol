pragma solidity ^0.8.12;

import "./UserContract.sol";
import "./PatientContract.sol";
import "../RegistryContract.sol";
import "./Relations/RelationsContract.sol";
import "../Account/IAccountController.sol";


contract DoctorContract is UserContract{

    constructor(address _owner, RegistryContract _registryContract) UserContract(_owner, _registryContract) {
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
    
    function addRecord(
        address patientAddress,
        bytes[] calldata patientData, 
        bytes[] calldata doctorData,
        address providerAddress, 
        bytes[] calldata providerData
    ) external hasRelation isPatient(patientAddress) {
        IAccountController accountController = IAccountController(registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT()));
        address permissions = PatientContract(accountController.getUserContractAddress(patientAddress)).addRecord(patientData, doctorData, providerAddress, providerData);
               
        addRecordInfo(address(permissions), patientAddress);
    }
}