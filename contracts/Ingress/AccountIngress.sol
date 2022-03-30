pragma solidity ^0.8.12;

import "../Account/IAccountProxy.sol";

contract AccountIngress {
    // version of this contract: semver eg 1.2.14 represented like 001002014
    address accountProxyAddress;
    address creator;

    modifier creatorOnly(){
        if (creator != address(0)){
            require(msg.sender == creator);
        }
        _;
    }

    function setAccountProxyAddress(address _address) external creatorOnly {
        accountProxyAddress = _address;
    }

    function transactionAllowed(
        address sender,
        address target,
        uint256 value,
        uint256 gasPrice,
        uint256 gasLimit,
        bytes calldata payload
    ) external view returns (bool) {
        if(accountProxyAddress == address(0)) {
            return true;
        }
        return IAccountProxy(accountProxyAddress).transactionAllowed(
            sender, target, value, gasPrice, gasLimit, payload
        );
    }
}