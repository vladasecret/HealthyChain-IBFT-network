pragma solidity ^0.8.12;

import "./INodeProxy.sol";

contract NodeIngress{

    address private nodeContract;

    address private adminContract;

    event NodePermissionsUpdated(bool addsRestrictions);

    function emitNodePermissionsEvent(bool addsRestrictions) external{
        emit NodePermissionsUpdated(addsRestrictions);
    }

    function connectionAllowed (
        string calldata enodeId,
        string calldata enodeHost,
        uint16 enodePort
    ) external view returns (bool){
//        return nodesProxy.connectionAllowed(enodeId, enodeHost, enodePort);
    }


}

