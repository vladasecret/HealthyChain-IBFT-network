//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

//import "./Libs/BytesConverter.sol";

contract AddressContract{

    function countAddress(string calldata pubKey) external pure returns(address){
        address res = address(uint160(uint256((keccak256(fromHex(pubKey))))));
        return res;        
    }

    function countAddress(bytes calldata pubKey) external pure returns(uint256, address){
        address res = address(uint160(uint256(keccak256(pubKey))));
        return (pubKey.length, res);        
    }

    function keccak(bytes calldata pubKey) external pure returns(bytes32){
        return keccak256(pubKey);
    }

    function keccak(string calldata pubKey) external pure returns(bytes32){
        return keccak256(fromHex(pubKey));
    }

    function fromHexChar(uint8 c) internal pure returns (uint8) {
        if (bytes1(c) >= bytes1('0') && bytes1(c) <= bytes1('9')) {
            return c - uint8(bytes1('0'));
        }
        if (bytes1(c) >= bytes1('a') && bytes1(c) <= bytes1('f')) {
            return 10 + c - uint8(bytes1('a'));
        }
        if (bytes1(c) >= bytes1('A') && bytes1(c) <= bytes1('F')) {
            return 10 + c - uint8(bytes1('A'));
        }
    }
    
    // Convert an hexadecimal string to raw bytes
    function fromHex(string memory self) public pure returns (bytes memory) {
        bytes memory ss = bytes(self);
        require(ss.length % 2 == 0); // length must be even
        bytes memory r = new bytes((ss.length - 2) / 2);
        for (uint i=0; i<(ss.length - 2) / 2 ; ++i) {
            r[i] = bytes1(fromHexChar(uint8(ss[2*i + 2])) * 16 +
                        fromHexChar(uint8(ss[2*i + 3])));
        }
        return r;
    }
    

}