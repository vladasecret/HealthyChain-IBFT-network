//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "../Account/IAccountProxy.sol";
import "../RegistryContract.sol";

contract AccountIngress {
    // version of this contract: semver eg 1.2.14 represented like 001002014
    RegistryContract registryContract;
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

    function setRegistyContract(address _address) external creatorOnly {
        registryContract = RegistryContract(_address);
    }

    function transactionAllowed(
        address sender,
        address target,
        uint256 value,
        uint256 gasPrice,
        uint256 gasLimit,
        bytes calldata payload
    ) external view returns (bool) {
        if(address(registryContract) == address(0)) {
            return true;
        }
        address accountProxyAddress = registryContract.getContractAddress(registryContract.ACCOUNT_CONTROLLER_CONTRACT());
        if (accountProxyAddress != address(0)){
            return IAccountProxy(accountProxyAddress).transactionAllowed(
                sender, target, value, gasPrice, gasLimit, payload
            );
        }
        return true;
    }
}