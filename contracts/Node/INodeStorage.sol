pragma solidity ^0.8.12;

struct Enode{
    string enodeId;
    string enodeHost;
    uint16 enodePort;
}

interface INodeStorage{
    function add(string memory enodeId, string memory enodeHost, uint16 enodePort) external returns(bool);
    function remove(string memory enodeId, string memory enodeHost, uint16 enodePort) external returns(bool);
    function exists(string memory enodeId, string memory enodeHost, uint16 enodePort) external view returns(bool);
    function exists(uint256 enodeKey) external view returns(bool);
    function size() external view returns (uint256);
    function getIndexOf(string memory enodeId, string memory enodeHost, uint16 enodePort) external view returns(uint256);
    function getByIndex(uint256 index) view external returns (string memory, string memory, uint16);
    function calculateKey(string memory enodeId, string memory enodeHost, uint16 enodePort) external view returns(uint256);
    function calculateKey(Enode memory enode) external view returns(uint256);
    function setIdOnlyMode(bool value) external returns (bool);
    function IdOnlyMode() external view returns (bool);
    function getEnodeIdNum(string calldata enodeId) external view returns(uint256);

}