pragma solidity ^0.8.12;

interface IAdminStorage{
    function size() external view returns(uint256);
    function getByIndex(uint256 index) external view returns(address);
    function getAllowlist() external view returns(address[] memory);
    function exists(address _address) external view returns(bool);
    function add(address _address) external;
    function remove(address _address) external;

}