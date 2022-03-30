pragma solidity ^0.8.12;

import "./IAdminStorage.sol";

contract AdminStorage is IAdminStorage{

    address[] allowlist;
    mapping(address => uint256) indexOf;

    address owner;

    modifier onlyOwner() {
        require(owner == msg.sender, "The sender must be the owner of the contract");
        _;
    }

    constructor() {
        owner = msg.sender;    
    }

    function size() external view returns(uint256){
        return allowlist.length;
    }

    //1 based array
    function getByIndex(uint256 index) external view returns(address) {
        require(index > 0 && index <= allowlist.length, "Out of bounds");
        return allowlist[index - 1];
    }

    function getAllowlist() external view returns(address[] memory) {
        return allowlist;
    }

    function exists(address _address) external view returns(bool){
        return indexOf[_address] != 0;
    }

    function add(address _address) onlyOwner external {
        if (indexOf[_address] == 0){
            allowlist.push(_address);
            indexOf[_address] = allowlist.length;
        }
    }

    function remove(address _address) onlyOwner external  {
        if (indexOf[_address] != 0){
            uint256 index = indexOf[_address];
            if (index != allowlist.length){
                address lastAddr = allowlist[allowlist.length - 1];
                indexOf[lastAddr] = index;
                allowlist[index - 1] = lastAddr;
            }

            indexOf[_address] = 0;
            allowlist.pop();
        }
    }
}