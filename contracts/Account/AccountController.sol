pragma solidity ^0.8.12;

import "./AccountStorage.sol";
import "./IAccountStorage.sol";
import "./IAccountProxy.sol";
import "../RegistryContract.sol";
import "../User/UserContract.sol";
import "../User/PatientContract.sol";
import "../User/DoctorContract.sol";

contract AccountController is IAccountProxy{
    IAccountStorage accountStorage;
    RegistryContract registryContract;
    address creator;

    modifier onlyCreator() {
        require(msg.sender == creator, "The sender must be the creator of the contract");
        _;
    }

    modifier onlyAdmin() {
        require(registryContract.isAdmin(msg.sender), "The sender must be admin");
        _;
    }

    modifier notSelf(address account) {
        require(account != msg.sender);
        _;
    }

    modifier onlyUser() {
        require(msg.sender == tx.origin, "The sender must be a user");
        _;
    }

    constructor (RegistryContract _registryContract){
        registryContract =_registryContract;
        accountStorage = new AccountStorage();
        creator = msg.sender;
    }

    function setAccountStorage(IAccountStorage _accountStorage) onlyCreator external {
        accountStorage = _accountStorage;
    }

    function isRegistered(address account) public view returns(bool){
        return accountStorage.exists(account) && accountStorage.getUserClass(account) != UserClass.DELETED;
    }

    function isAuthorized(address account)  public view returns(bool){
        return accountStorage.exists(account) && accountStorage.getUserClass(account) != UserClass.DELETED;
    }

    function isUser(address _address) internal view returns(bool){
        return _address.code.length == 0;
    }

    function transactionAllowed(
        address sender,
        address target,
        uint256 value,
        uint256 gasPrice,
        uint256 gasLimit,
        bytes calldata payload
    ) external view returns (bool){
        if (gasPrice != 0)
            return false;
        if (isUser(sender)){
            if (!(isRegistered(sender) || registryContract.isAdmin(sender)))
                return false;
            if ((target == address(0) && sender != creator) || isUser(target))
                return false;
        }
        return true;
    }

    function getUserContractAddress(address account) external view returns(address){
        require(isRegistered(account), "Unable to get UserContract: user not registered");
        return accountStorage.getUserContractAdrress(account);
        
    }

    function registryProvider(address account) onlyAdmin external {
        require(isUser(account), "Registered user address cannot have a code");
        require(!isRegistered(account), "This user is already registered");
        require(registryContract.isAdmin(account), "To register an address as a provider, it must be an administrator");  
        address userContractAddress = address(new UserContract(account, registryContract));
        accountStorage.add(account, UserClass.PROVIDER, userContractAddress);
    }

    function registryDoctor(address account) onlyAdmin external {
        require(isUser(account), "Registered user address cannot have a code");
        require(!isRegistered(account), "This user is already registered");

        address userContractAddress = address(new DoctorContract(account, registryContract));
        accountStorage.add(account, UserClass.DOCTOR, userContractAddress);

    }

    function registryPatient(address account) external {
        require(isUser(account), "Registered user address cannot have a code");
        require(!isRegistered(account), "This user is already registered");

        address userContractAddress = address(new PatientContract(account, registryContract));
        accountStorage.add(account, UserClass.PATIENT, userContractAddress);
    }


    

}