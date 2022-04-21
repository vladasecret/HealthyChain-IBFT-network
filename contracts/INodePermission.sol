pragma solidity ^0.8.12;

interface INodePermission {
    /// @param sourceEnode The enode address of the node initiating the connection
    /// @param source A DNS name of the node initiating the connection
    /// @param sourceEnodePort The peer-to-peer listening port of the node initiating the connection
    function connectionAllowed(
        string calldata sourceEnode,
        string calldata source,
        uint16 sourceEnodePort
    ) external view returns (bool);


    /// @param addsRestrictions If the rules change that caused the NodePermissionsUpdated event to be emitted involves further restricting existing permissions, this will be true, otherwise false*/ 
    /// @param addsPermissions If the rules change that caused the NodePermissionsUpdated event to be emitted involves granting new permissions, this will be true, otherwise false.     */
    /// @param enodeId The enode address of the node for which the permissions have changed
    /// @param source The valid host string [url] of the node whose permissions have changed.
    /// @param port The peer-to-peer listening port of the node for which the permissions have changed
    /// @param raftport When using the RAFT consensus protocol, the raft port of the node for which the permissions have changed
    event NodePermissionsUpdated(
        bool addsRestrictions, 
        bool addsPermissions, 
        string enodeId, 
        string source, 
        uint16 port,
        uint16 raftport,
        string orgId);
                                

}