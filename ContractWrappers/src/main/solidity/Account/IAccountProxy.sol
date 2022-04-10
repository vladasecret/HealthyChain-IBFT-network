//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;


interface IAccountProxy{
    function transactionAllowed(
        address sender,
        address target,
        uint256 value,
        uint256 gasPrice,
        uint256 gasLimit,
        bytes calldata payload
    ) external view returns (bool);
}