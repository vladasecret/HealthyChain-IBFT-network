//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "../User/UserContract.sol";
import "./IAccountStorage.sol";

contract AccountStorage is IAccountStorage{

    AccountInfo[] allowlist;
    mapping(address => uint256) indexOf;

    address owner;

    modifier onlyOwner() {
        require(owner == msg.sender, "The sender must be the owner of the contract");
        _;
    }

    constructor() {
        owner = msg.sender;
    }

    function size() public view returns (uint256) {
        return allowlist.length;
    }

    function exists(address account) public view returns (bool) {
        return indexOf[account] != 0;
    }

    function add(address account, UserClass class, address userContractAddress) public onlyOwner returns (bool) {
        if (indexOf[account] == 0) {           
            allowlist.push(AccountInfo(account, class, userContractAddress));
            indexOf[account] = allowlist.length;
            return true;
        }
        return false;
    }

    function remove(address account) public onlyOwner {
        uint256 index = indexOf[account];
        if (index > 0 && index <= allowlist.length) { //1-based indexing
            allowlist[allowlist.length - 1].class = UserClass.DELETED;
//            if (index != allowlist.length) {
//                AccountInfo memory lastAccount = allowlist[allowlist.length - 1];
//                allowlist[index - 1] = lastAccount;
//                indexOf[lastAccount.accountAddress] = index;
//            }
//            allowlist.pop();
//            indexOf[account] = 0;
//            return true;
        }
    }

    function getUserClass(address user) public view  returns(UserClass){
        uint index = indexOf[user];
        require(index > 0, "Account not registered");
        return allowlist[index - 1].class;
    }

    function getUserContractAddress(address user) external view returns(address){
        return allowlist[indexOf[user] - 1].userContractAddress;
    }

    function getAccountInfo(address account) public view returns(address, UserClass, address){
        AccountInfo storage info = allowlist[indexOf[account] - 1];
        return (info.accountAddress, info.class, info.userContractAddress);
    }
}