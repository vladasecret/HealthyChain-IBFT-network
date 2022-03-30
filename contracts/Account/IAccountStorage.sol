pragma solidity ^0.8.12;

import "../User/UserContract.sol";

enum UserClass {PATIENT, DOCTOR, PROVIDER, DELETED}

struct AccountInfo{
    address accountAddress;
    UserClass class;
    address userContractAddress;
}

interface IAccountStorage{
    function size() external view returns (uint256);
    function exists(address user) external view returns (bool);
    function add(address user, UserClass class, address userContractAddress) external returns (bool);
    function remove(address user) external  returns (bool);
    function getUserClass(address user) external view returns(UserClass);
    function getUserContractAdrress(address user) external view  returns(address);
    function getAccountInfo(address user) external view returns(address, UserClass, address);
} 