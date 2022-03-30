//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/Node/NodeController.sol";
import "../contracts/RegistryContract.sol";
import "../contracts/Admin/Admin.sol";

contract TestNodeController{
    RegistryContract registryContract; 
    NodeController nodesController;
    Admin admin;

    string[] enodes;
    string constant DEF_HOST = "127.0.0.1";
    uint16 constant DEF_PORT= 3030;
    
    constructor(){
        registryContract = new RegistryContract();
        admin = new Admin(registryContract);
        registryContract.setContractAddress(registryContract.ADMIN_CONTRACT(), address(admin));

        nodesController = new NodeController(address(registryContract), address(0));
        registryContract.setContractAddress(registryContract.NODE_CONTROLLER_CONTRACT(), address(nodesController));

        enodes.push("0x93df0dab486fc77ff7285f1d3fed4992ce96631af1c66f338acab9bb57deb3cdb2a2e6228f684b3058f8b214a8682a485e45c4c7f4047bd9f4a1e39d33ff47f3");
        enodes.push("0x93df0dab486fc77ff7285f1d3fed4992ce96631af1c66f338acab9bb57deb3cdb2a2e6228f684b3058f8b214a8682a485e45c4c7f4047bd9f4a1e39d33ff47f9");
        enodes.push("0x2b3b8f4bc24db317122711da044f4f6998aad429e31942fef51d1a5da2be59fa98a8a96e0561a910cbc9633975679594b4e8c0feb29bb86dca0b59af5438d3bb");
        enodes.push("0xa8cbd41e9f3235e01a95971636507ff8e4f18321fbff525f3b7244129da524c7703222d464c8b23c69ae11932e97d998f2943a5c6c175551743476b64ee36b78");
        enodes.push("0xcb38875a0712bf59e8a96f6e3bb84b35b877b89880c043d248e3f838f9497e1fab229150bd461cb12478afd8e919e74de497dabd47b509a8c1f2c655faa0b548");
    }


    function testGetDefaultSenderVote() public{
        Assert.equal(uint8(nodesController.getSenderProposal(enodes[0], DEF_HOST, DEF_PORT)), uint8(NodeController.ProposalStatus.NONE), "Default value isn't NONE");
    }
    
    function testAddThreeNodes() public {   
        for (uint256 i = 0; i < 3; ++i){
            bool res = nodesController.add(enodes[i], DEF_HOST, DEF_PORT);
            assert(res == true);
            Assert.equal(nodesController.connectionAllowed(enodes[i], DEF_HOST, DEF_PORT), true, "Node must be allowed");
            Assert.equal(admin.isAuthorized(nodesController.countAddress(enodes[i])), true, "Administrator not added when adding a node");
        }
    }   
    
    function testAddFourthNode() public {
        bool res = nodesController.add(enodes[3], DEF_HOST, DEF_PORT);
        assert(res == true);
        Assert.equal(nodesController.connectionAllowed(enodes[3], DEF_HOST, DEF_PORT), false, "Node mustn't be allowed");
        Assert.equal(uint8(nodesController.getSenderProposal(enodes[3], DEF_HOST, DEF_PORT)), uint8(NodeController.ProposalStatus.ADD), "Vote save error");
    }

    function testDeleteNodeFailture() public {
        Assert.equal(uint8(nodesController.getSenderProposal(enodes[3], DEF_HOST, DEF_PORT)), uint8(NodeController.ProposalStatus.ADD), "Vote must be ADD");
        bool res = nodesController.remove(enodes[3], DEF_HOST, DEF_PORT);
        Assert.equal(res, false, "Node being added has been deleted");
    }

    function testRevokeProposal() public {
        Assert.equal(uint8(nodesController.getSenderProposal(enodes[3], DEF_HOST, DEF_PORT)), uint8(NodeController.ProposalStatus.ADD), "Vote must be ADD");
        nodesController.revokeProposal(enodes[3], DEF_HOST, DEF_PORT);
        Assert.equal(uint8(nodesController.getSenderProposal(enodes[3], DEF_HOST, DEF_PORT)), uint8(NodeController.ProposalStatus.NONE), "Vote must be NONE");
    }

    function testDeleteNodeSuccess() public {
        Assert.equal(uint8(nodesController.getSenderProposal(enodes[0], DEF_HOST, DEF_PORT)), uint8(NodeController.ProposalStatus.NONE), "Vote must be NONE");
        bool res = nodesController.remove(enodes[0], DEF_HOST, DEF_PORT);
        Assert.equal(res, true, "Failed to vote for deletion");
        Assert.equal(nodesController.connectionAllowed(enodes[0], DEF_HOST, DEF_PORT), false, "Failed to delete");        
    }

    
}