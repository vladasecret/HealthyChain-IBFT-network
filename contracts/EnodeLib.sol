pragma solidity ^0.8.12;

struct Enode{
    string enodeId;
    string enodeHost;
    uint16 enodePort;
}

library EnodeLib{
    function calculateKey(Enode memory enode, bool idOnlyMode) public pure returns(uint256){
        return calculateKey(enode.enodeId, enode.enodeHost, enode.enodePort, idOnlyMode);
    }

    function calculateKey(string memory enodeId, string memory enodeHost, uint16 enodePort, bool idOnlyMode) public pure returns(uint256){
        if (idOnlyMode){
            return calculateKey(enodeId);
        }
        return uint256(keccak256(abi.encodePacked(enodeId, enodeHost, enodePort)));
    }

    function calculateKey(string memory enodeId, string memory enodeHost, uint16 enodePort) public pure returns(uint256){
        return uint256(keccak256(abi.encodePacked(enodeId, enodeHost, enodePort)));
    }

    function calculateKey(string memory enodeId) public pure returns(uint256){
            return uint256(keccak256(abi.encodePacked(enodeId)));        
    }
}