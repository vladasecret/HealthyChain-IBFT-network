//SPDX-License-Identifier: Apache-2.0
pragma solidity  ^0.8.12;

import "./AdminProxy.sol";
import "./IAdminStorage.sol";
import "./AdminStorage.sol";
import "../RegistryContract.sol";

contract Admin is AdminProxy{
    IAdminStorage adminStorage;
    RegistryContract registryContract;
    address creator;

    event AdminAdded(address admin);
    event AdminRemoved(address admin);

    modifier onlyCreator() {
        require(creator == msg.sender, "The sender must be the creator of the contract");
        _;
    }

    modifier onlyNodeController(){
        require(registryContract.getContractAddress(registryContract.NODE_CONTROLLER_CONTRACT()) == msg.sender,
                "The sender must be the NodeController contract");
        _;
    }

    modifier isUser(address _address){
        require(_address.code.length == 0, "");
        _;
    }

    constructor(RegistryContract _registryContract){
        registryContract = _registryContract;
        adminStorage = new AdminStorage();
        creator = msg.sender;
        adminStorage.add(msg.sender);
    }

    function setAdminStorage(IAdminStorage newStorage) external onlyCreator{
        adminStorage = newStorage;
    }

    function getAdminStorage() external view returns (IAdminStorage){
        return adminStorage;
    }

    function isAuthorized(address source) external view returns (bool){
        return adminStorage.exists(source);
    }

    function add(address admin) external onlyNodeController isUser(admin){
        adminStorage.add(admin);
        emit AdminAdded(admin);
    }

    function remove(address admin) external onlyNodeController {
        adminStorage.remove(admin);
        emit AdminRemoved(admin);
    }

    function size() external view returns(uint256){
        return adminStorage.size();
    }

}