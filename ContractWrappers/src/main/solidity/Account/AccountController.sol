//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "./AccountStorage.sol";
import "./IAccountStorage.sol";
import "./IAccountProxy.sol";
import "../RegistryContract.sol";
import "../User/IUserContractFactory.sol";

/// @notice Contract to manage relationships and add/get metadata and grant access
contract AccountController is IAccountProxy{
    IAccountStorage accountStorage;
    IUserContractFactory userContractFactory;
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
    
    constructor (RegistryContract _registryContract, IUserContractFactory _userContractFactory){
        registryContract =_registryContract;
        accountStorage = new AccountStorage();
        userContractFactory = _userContractFactory;
        creator = msg.sender;
    }

    /// @notice Sets a new contract IAccountStorage
    /// @dev Can only be called by the creator
    /// @param _accountStorage The contract that implements the interface IAccountStorage
    function setAccountStorage(IAccountStorage _accountStorage) onlyCreator external {
        accountStorage = _accountStorage;
    }

    /// @notice Sets a new contract IUserContractFactory
    /// @dev Can only be called by the creator
    /// @param _userContractFactory The contract that implements the interface IUserContractFactory
    function setUserContractsFactory(IUserContractFactory _userContractFactory) onlyCreator external {
        userContractFactory = _userContractFactory;
    }

    function isRegistered(address account) public view returns(bool){
        return accountStorage.exists(account) && accountStorage.getUserClass(account) != UserClass.DELETED;
    }

    // function isAuthorized(address account)  public view returns(bool){
    //     return accountStorage.exists(account) && accountStorage.getUserClass(account) != UserClass.DELETED;
    // }

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
        if (gasPrice != 0 || value != 0)
            return false;
        if (isUser(sender)){
            if (!(isRegistered(sender) || registryContract.isAdmin(sender)))
                return false;
            if ((target == address(0) && sender != creator) || isUser(target))
                return false;
        }
        return true;
    }

    /// @notice Returns the address of the User Contract owned by the specified user
    /// @dev Will be reverted if the specified user is not registered
    /// @param account Ethereum address of the account whose contract we want to receive
    function getUserContractAddress(address account) external view returns(address){
        require(isRegistered(account), "Unable to get UserContract: user not registered");
        return accountStorage.getUserContractAddress(account);  
    }

    /// @notice Adds a new user with the provider class to AccountStorage. The specified account must be registered as an administrator. Function can only be called by another administrator
    /// @dev Will be reverted if the specified user is not user (address associated with code), already registered, is not admin
    /// @param account Ethereum address of the user we want to register
    function registryProvider(address account) onlyAdmin external {
        require(isUser(account), "Registered user address cannot have a code");
        require(!isRegistered(account), "This user is already registered");
        require(registryContract.isAdmin(account), "To register an address as a provider, it must be an administrator");  
        address userContractAddress = userContractFactory.create(account, UserClass.PROVIDER);
        accountStorage.add(account, UserClass.PROVIDER, userContractAddress);
    }

    /// @notice Adds a new user with the doctor class to AccountStorage. Function can only be called by administrator
    /// @dev Will be reverted if the specified user is not user (address associated with code), already registered
    /// @param account Ethereum address of the user we want to register
    function registryDoctor(address account) onlyAdmin external {
        require(isUser(account), "Registered user address cannot have a code");
        require(!isRegistered(account), "This user is already registered");

        address userContractAddress = userContractFactory.create(account, UserClass.DOCTOR);
        accountStorage.add(account, UserClass.DOCTOR, userContractAddress);

    }

    /// @notice Adds a new user with the patient class to AccountStorage. Function can only be called by administrator
    /// @dev Will be reverted if the specified user is not user (address associated with code), already registered
    /// @param account Ethereum address of the user we want to register
    function registryPatient(address account) external {
        require(isUser(account), "Registered user address cannot have a code");
        require(!isRegistered(account), "This user is already registered");

        address userContractAddress = userContractFactory.create(account, UserClass.PATIENT);
        accountStorage.add(account, UserClass.PATIENT, userContractAddress);
    }
}