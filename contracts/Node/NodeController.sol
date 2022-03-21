// SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "./INodeStorage.sol";
import "./NodeStorage.sol";
import "./INodeProxy.sol";
import "./NodeIngress.sol";
//import "../contracts/EnodeLib.sol";

contract NodeController is INodeProxy{

    
    struct Proposal {
        Enode enode;
        ProposalStatus status;
        address[] voters;
    }
    

    enum ProposalStatus {NONE, ADD, REMOVE}

    event NodeAdded(bool added, string enodeId, string enodeHost, uint16 enodePort);
    event NodeRemoved(bool removed, string enodeId, string enodeHost, uint16 enodePort);
    event ProposalAdded(ProposalStatus status, uint256 key, address sender);

    //address immutable owner;

    NodeIngress nodeIngress;    
    INodeStorage nodeStorage;

    Proposal[] proposals;
    mapping(uint256 => uint256) indexOf;
    mapping(address=> mapping(uint256 => ProposalStatus)) accountProposals;

    constructor(NodeIngress _nodeIngress) {
        nodeStorage = new NodeStorage();
        nodeIngress = _nodeIngress; 
    }

    function connectionAllowed (string calldata enodeId, string calldata enodeHost, uint16 enodePort) external view returns (bool){
        return nodeStorage.exists(enodeId, enodeHost, enodePort);
    }

    function add(string calldata enodeId, string calldata enodeHost, uint16 enodePort) external returns(bool){
        uint256 key = nodeStorage.calculateKey(enodeId, enodeHost, enodePort);
        if (nodeStorage.exists(key) || (accountProposals[msg.sender][key] == ProposalStatus.ADD)){
            return false;
        }          
        
        if (canBeAdded(key)){         
            bool added = nodeStorage.add(enodeId, enodeHost, enodePort);        
            emit NodeAdded(added, enodeId, enodeHost, enodePort);
            clearProposal(key);
            if (added){

            }
            
        }
        else {
            addVote(ProposalStatus.REMOVE, key, enodeId, enodeHost, enodePort);
        }
        return true;
    }

    function remove(string calldata enodeId, string calldata enodeHost, uint16 enodePort) external returns(bool){
        uint256 key = nodeStorage.calculateKey(enodeId, enodeHost, enodePort);
        if (!nodeStorage.exists(enodeId, enodeHost, enodePort) || accountProposals[msg.sender][key] == ProposalStatus.REMOVE){
            return false;
        }

        if (canBeRemoved(key)){
            nodeStorage.remove(enodeId, enodeHost, enodePort);
            clearProposal(key);
        }
        else {
            addVote(ProposalStatus.REMOVE, key, enodeId, enodeHost, enodePort);
        }
        return true;
    }

    function addVote(ProposalStatus status, uint256 key, string memory enodeId, string memory enodeHost, uint16 enodePort) internal {  
            require(status != accountProposals[msg.sender][key]);

            if (indexOf[key] == 0){
                proposals.push(Proposal(Enode(enodeId, enodeHost, enodePort), status, new address[](0)));
                indexOf[key] = proposals.length;
            }
            Proposal storage proposal = proposals[indexOf[key] - 1];
            require(proposal.status == status);
            proposal.voters.push(msg.sender);
            accountProposals[msg.sender][key] = status;
    }

    function clearProposal(uint256 key) internal {
            uint256 index = indexOf[key];
            if (index == 0)
                return;
            
            address[] storage voters = proposals[index - 1].voters;
            for (uint i = 0; i < voters.length; i++){
                accountProposals[voters[i]][key] = ProposalStatus.NONE;
            }
            delete proposals[index - 1].voters;

            if (index != proposals.length){
                Proposal memory lastProposal  = proposals[proposals.length - 1];
                indexOf[nodeStorage.calculateKey(lastProposal.enode)] = index;
                proposals[index - 1] = lastProposal;
            }

            indexOf[key] = 0;
            proposals.pop();
    }

    function getSenderProposal(string calldata enodeId, string calldata enodeHost, uint16 enodePort) external view returns(ProposalStatus){
        uint256 key = nodeStorage.calculateKey(enodeId, enodeHost, enodePort);
        return accountProposals[msg.sender][key];
    }

    function revokeProposal(string calldata enodeId, string calldata enodeHost, uint16 enodePort) external returns(bool changed){
        uint256 key = nodeStorage.calculateKey(enodeId, enodeHost, enodePort);
        if (accountProposals[msg.sender][key] != ProposalStatus.NONE){
            accountProposals[msg.sender][key] = ProposalStatus.NONE;
            changed = true;
        }        
    }

    function setIdOnlyMode(bool value) external returns (bool changed){
        if (value != nodeStorage.IdOnlyMode()){
            uint256 len = proposals.length;
            for (uint256 i = 0; i < len; ++i){
                Proposal storage proposal = proposals[i];
                uint256 key = nodeStorage.calculateKey(proposal.enode);//nodeStorage.calculateKey(enodeId, enodeHost, enodePort);

                uint256 votesLen = proposal.voters.length;
                for (uint256 j = 0; j < votesLen; ++i){
                    accountProposals[proposal.voters[j]][key] = ProposalStatus.NONE;
                }

                indexOf[key] = 0;
            }

            require(nodeStorage.setIdOnlyMode(value) == true, "Failed to switch IdOnlyMode");

            for (uint256 i = 0; i < len; ++i){
                Proposal storage proposal = proposals[i];
                uint256 key = nodeStorage.calculateKey(proposal.enode);//nodeStorage.calculateKey(enodeId, enodeHost, enodePort);

                uint256 votesLen = proposal.voters.length;
                for (uint256 j = 0; j < votesLen; ++i){
                    accountProposals[proposal.voters[j]][key] = proposal.status;
                }

                indexOf[key] = i + 1;
            }

            changed = true;
        }
    }

    function canBeAdded(uint256 key) internal view returns(bool){
        Proposal storage proposal = proposals[indexOf[key] - 1];
        return proposal.status == ProposalStatus.ADD && proposal.voters.length + 1 >= votesRequiredAdd();       // ПЕРЕДЕЛАТЬ
    }

    function canBeRemoved(uint256 key) internal view returns(bool){
        Proposal storage proposal = proposals[indexOf[key] - 1];
        return proposal.status == ProposalStatus.REMOVE && proposal.voters.length + 1 >= votesRequiredRemove();       // ПЕРЕДЕЛАТЬ
    }

    function votesRequiredAdd() internal view returns(uint256){
        return nodeStorage.size() / 3 * 2;        
    }

    function votesRequiredRemove() internal view returns(uint256){
        return nodeStorage.size() / 2;        
    }


}