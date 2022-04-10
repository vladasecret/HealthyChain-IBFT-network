//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

interface AdminProxy {
    function isAuthorized(address source) external view returns (bool);  
}