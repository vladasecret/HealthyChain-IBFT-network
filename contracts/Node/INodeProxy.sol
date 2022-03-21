pragma solidity ^0.8.12;


interface INodeProxy {
        function connectionAllowed (
        string calldata enodeId,
        string calldata enodeHost,
        uint16 enodePort
    ) external view returns (bool);
}