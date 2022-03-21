pragma solidity ^0.8.12;

import "./INodeProxy.sol";

contract NodeIngress{

    address private nodesProxyAddress;
    //address private adminsContract;
    address creator;

    modifier creatorOnly(){
        if (creator != address(0)){
            require(msg.sender == creator);
        }
        _;
    }

    function setNodesProxyAddress(address _nodesProxyAddress) external creatorOnly{
        nodesProxyAddress = _nodesProxyAddress;
    }

    event NodePermissionsUpdated(bool addsRestrictions);

    function emitNodePermissionsEvent(bool addsRestrictions) external{
        emit NodePermissionsUpdated(addsRestrictions);
    }

    function connectionAllowed (
        string calldata enodeId,
        string calldata enodeHost,
        uint16 enodePort
    ) external view returns (bool){
        if (nodesProxyAddress != address(0))
            return INodeProxy(nodesProxyAddress).connectionAllowed(enodeId, enodeHost, enodePort);
        return false;
    }


}

