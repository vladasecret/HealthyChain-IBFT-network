//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "./IAccountStorage.sol";


interface IAccountController{
    function isRegistered(address account) external view returns(bool);
    //function isAuthorized(address account) external view returns(bool);
    function getUserContractAddress(address account) external returns(address);
    function getUserClass(address _address) external view returns(UserClass); 
    //function isUser(address _address) external view returns(bool);



}