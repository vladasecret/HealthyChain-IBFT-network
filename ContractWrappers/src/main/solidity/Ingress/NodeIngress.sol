//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "../Node/INodeProxy.sol";
import "../RegistryContract.sol";

contract NodeIngress{

    RegistryContract registryContract;
    //address private adminsContract;
    address creator;

    modifier creatorOnly(){
        if (creator != address(0)){
            require(msg.sender == creator, "The sender must be creator of the contract");
        }
        _;
    }

    constructor(){
        creator = msg.sender;
    }

    function setRegistryContract(address _registryContractAddress) external creatorOnly{
        registryContract = RegistryContract(_registryContractAddress);
    }

    event NodePermissionsUpdated(bool addsRestrictions);

    function emitNodePermissionsEvent(bool addsRestrictions) external{
        emit NodePermissionsUpdated(addsRestrictions);
    }

    function connectionAllowed (
        string calldata enodeId,
        string calldata enodeHost,
        uint16 enodePort
    ) external view returns (bool){
        if (address(registryContract) == address(0))
            return true;
        address nodeProxyAddress = registryContract.getContractAddress(registryContract.NODE_CONTROLLER_CONTRACT());
        if (nodeProxyAddress != address(0))
            return INodeProxy(nodeProxyAddress).connectionAllowed(enodeId, enodeHost, enodePort);
        return true;
    }


}

