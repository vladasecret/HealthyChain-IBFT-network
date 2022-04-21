pragma solidity ^0.8.11;

import "./INodeStorage.sol";
//import "../contracts/EnodeLib.sol";


contract NodeStorage is INodeStorage {
     
    address owner;

    constructor(){
        owner = msg.sender;
    }
    
    Enode[] public allowlist; // 1 based array
    mapping(uint256 => uint256) indexOf; // 0 means don't exist

    bool public IdOnlyMode = true;    
    mapping(string => uint256) enodeIdNum;

    modifier onlyOwner() {
        require(owner == msg.sender, "The sender must be the owner of the contract");
        _;
    }

    function setOwner(address _address) onlyOwner() external {
        owner = _address;
    }

    function getByIndex(uint256 index) view external returns (string memory, string memory, uint16){
        require(index >= 1 && index <= allowlist.length, "Out of bounds");
        Enode memory item = allowlist[index - 1];
        return (item.enodeId, item.enodeHost, item.enodePort);
    }

    function getEnodeIdNum(string calldata enodeId) external view returns(uint256){
        return enodeIdNum[enodeId];
    }

    function add(string memory enodeId, string memory enodeHost, uint16 enodePort) onlyOwner public returns (bool){
        //uint256 key = EnodeLib.calculateKey(enodeId, enodeHost, enodePort, IdOnlyMode);               
        uint256 key = calculateKey(enodeId, enodeHost, enodePort);               
        if (indexOf[key] != 0)  {
            return false;
        }

        allowlist.push(Enode(enodeId,enodeHost, enodePort));
        indexOf[key] = allowlist.length;
        enodeIdNum[enodeId] += 1;
        
        return true;
    }

    function remove(string memory enodeId, string memory enodeHost, uint16 enodePort) onlyOwner public returns(bool) {
        uint256 key = calculateKey(enodeId, enodeHost, enodePort);

        uint256 index = indexOf[key];
        assert(index <= allowlist.length);
        if (index < 1){
            return false;
        }

        if (index != allowlist.length){
            Enode memory enode = allowlist[allowlist.length - 1];
            allowlist[index - 1] = enode;
            uint256 lastKey = calculateKey(enode);
            if (indexOf[lastKey] != 0)
                indexOf[lastKey] = index;
        }

        indexOf[key] = 0;       
        allowlist.pop();
        enodeIdNum[enodeId] -= 1;
        return true;
    }
    
    function setIdOnlyMode(bool value) onlyOwner public returns (bool) {
        if (value == IdOnlyMode)
            return false;

        for (uint i = 0; i < allowlist.length; i++){
            Enode memory enode = allowlist[i];
            indexOf[calculateKey(enode)] = 0;
            enodeIdNum[enode.enodeId] = 0;
        }

        IdOnlyMode = value;

        for (uint i = 0; i < allowlist.length; i++){
            Enode memory enode = allowlist[i];
            uint256 key = calculateKey(enode);
            if (indexOf[key] == 0){
                enodeIdNum[enode.enodeId] += 1;
            }
            indexOf[key] = i + 1;
        }
        return true;
    }

    function getIndexOf(string memory enodeId, string memory enodeHost, uint16 enodePort) external view returns(uint256) {
        uint256 key = calculateKey(enodeId, enodeHost, enodePort);
        return indexOf[key];
    }

    function exists(string memory enodeId, string memory enodeHost, uint16 enodePort) public view returns (bool){
        return indexOf[calculateKey(enodeId, enodeHost, enodePort)] != 0;
    }

    function exists(uint256 key) public view returns (bool){
        return indexOf[key] != 0;
    }
    
    function calculateKey(string memory enodeId, string memory enodeHost, uint16 enodePort) public view returns(uint256){
        if (IdOnlyMode){
            return uint256(keccak256(abi.encodePacked(enodeId)));
        }
        return uint256(keccak256(abi.encodePacked(enodeId, enodeHost, enodePort)));
    }

    function calculateKey(Enode memory enode) public view returns(uint256){
        if (IdOnlyMode){
            return uint256(keccak256(abi.encodePacked(enode.enodeId)));
        }
        return uint256(keccak256(abi.encodePacked(enode.enodeId, enode.enodeHost, enode.enodePort)));
    }
    

    function size() external view returns (uint256){
        return allowlist.length;
    }
}