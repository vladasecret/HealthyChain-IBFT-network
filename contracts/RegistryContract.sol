pragma solidity ^0.8.12;

import "./Admin/AdminProxy.sol";
import "./Account/IAccountController.sol";

contract RegistryContract{
    bytes32 public NODE_CONTROLLER_CONTRACT = bytes32("nodeController"); 
    bytes32 public ACCOUNT_CONTROLLER_CONTRACT = bytes32("accountController");
    bytes32 public ADMIN_CONTRACT = bytes32("admins"); 

    address creator;

    // Registry mapping indexing
    mapping(bytes32 => address) internal registry;

    address[] internal contracts;
    mapping (address => uint256) internal indexOf; //1 based indexing. 0 means non-existent

    event RegistryUpdated(
        address contractAddress,
        bytes32 contractName
    );

    modifier onlyCreator() {
        if (address(0) != creator)
            require(creator == msg.sender, "The sender must be the creator of the contract");
        _;
    }

    constructor() {
        creator = msg.sender;
    }

    function hasContractAddress(bytes32 name) external view returns(bool){
        require(name > 0, "Contract name must not be empty.");
        return registry[name] != address(0);
    }

    function getContractAddress(bytes32 name) public view returns(address) {
        require(name > 0, "Contract name must not be empty.");
        return registry[name];
    }

    function getSize() external view returns (uint256) {
        return contracts.length;
    }

    // function isRegistered(address account) public view returns(bool) {
    //     if (registry[NODE_CONTROLLER_CONTRACT] == address(0)){
    //         return true;
    //     }
    //     return IAccountController(registry[NODE_CONTROLLER_CONTRACT]).isRegistered(account);
    // }

    function isAuthorized(address account) external view returns(bool) {
        if (registry[NODE_CONTROLLER_CONTRACT] == address(0)){
            return true;
        }
        return IAccountController(registry[NODE_CONTROLLER_CONTRACT]).isRegistered(account);
    }


    function isAdmin(address account) public view returns(bool) {
        if (registry[ADMIN_CONTRACT] == address(0)) {
            return true;
        } 
        else {
            return AdminProxy(registry[ADMIN_CONTRACT]).isAuthorized(account);
        }
    }

    function setContractAddress(bytes32 name, address addr) external onlyCreator returns (bool) {
        require(name > 0, "Contract name must not be empty.");
        require(addr != address(0), "Contract address must not be zero.");

        if (indexOf[addr] == 0) {
            contracts.push(addr);
            indexOf[addr] = contracts.length;
        }

        registry[name] = addr;

        emit RegistryUpdated(addr, name);

        return true;
    }

    function removeContract(bytes32 _name) external onlyCreator returns(bool) {
        require(_name > 0, "Contract name must not be empty.");
        require(contracts.length > 0, "Must have at least one registered contract to execute delete operation.");
        
        uint256 index = indexOf[registry[_name]];
        if (index > 0 && index <= contracts.length) { //1-based indexing
            //move last address into index being vacated (unless we are dealing with last index)
            if (index != contracts.length) {
                address lastKey = contracts[contracts.length - 1];
                contracts[index - 1] = lastKey;
                indexOf[lastKey] = index;
            }

            //shrink contract keys array
            contracts.pop();
            indexOf[registry[_name]] = 0;
            registry[_name] = address(0);
            emit RegistryUpdated(address(0), _name);
            return true;
        }
        return false;
    }

    function getAllContractKeys() external view returns(address[] memory) {
        return contracts; // mythx-disable-line SWC-128
    }
}