pragma solidity ^0.8.12;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/Node/NodeController.sol";
import "../contracts/Node/NodeIngress.sol";

contract TestNodesController{
    NodeController nodesController = new NodeController(NodeIngress(address(0)));

}