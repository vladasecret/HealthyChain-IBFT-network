/// SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.12;

import "./INodeStorage.sol";
import "./NodeStorage.sol";
import "./INodeProxy.sol";
import "../Ingress/NodeIngress.sol";
import "../RegistryContract.sol";
import "../Libs/BytesConverter.sol";
import "../Admin/Admin.sol";



contract NodeController is INodeProxy{

    enum ProposalStatus {NONE, ADD, REMOVE}
    
    struct Proposal {
        Enode enode;
        ProposalStatus status;
        address[] voters;
    }

    event NodeAdded(bool added, string enodeId, string enodeHost, uint16 enodePort);
    event NodeRemoved(bool removed, string enodeId, string enodeHost, uint16 enodePort);
    event ProposalAdded(uint256 key, ProposalStatus status, address sender);
    event ProposalRemoved(uint256 key, ProposalStatus status, address sender);

    address immutable creator;


    NodeIngress nodeIngress;    
    INodeStorage nodeStorage;
    RegistryContract registryContract;

    Proposal[] proposals;
    mapping(uint256 => uint256) indexOf;

    //mappint account => enode key => account index in enode Proposal (0 = !exists)
    mapping(address=> mapping(uint256 => uint256)) accountProposals;

    modifier onlyCreator(){
        require(msg.sender == creator, "The sender must be the creator of this contract");
        _;
    }

    modifier onlyAdmin(){
        if (address(registryContract) != address(0))
            require(registryContract.isAdmin(msg.sender), "The sender of the transaction must be an administrator");
        _;
    }

    constructor(address registry, address _nodeIngressAddress) {
        creator = msg.sender;
        nodeStorage = new NodeStorage();
        nodeIngress = NodeIngress(_nodeIngressAddress); 
        registryContract = RegistryContract(registry);
    }

    function setStorage(INodeStorage _nodeStorage) external onlyCreator {
        nodeStorage = _nodeStorage;
    }

    function getVoters(string memory enodeId, string memory enodeHost, uint16 enodePort) view external returns(address[] memory){
        uint256 key = nodeStorage.calculateKey(enodeId, enodeHost, enodePort);
        if (indexOf[key] != 0){
            return proposals[indexOf[key] - 1].voters;
        }
        return new address[](0);
    }

    function connectionAllowed (string calldata enodeId, string calldata enodeHost, uint16 enodePort) external view returns (bool){
        return nodeStorage.exists(enodeId, enodeHost, enodePort);
    }

    function triggerNodesChangeEvent(bool addsRestrictions) internal {
        if (address(nodeIngress) != address(0))
            nodeIngress.emitNodePermissionsEvent(addsRestrictions);
    }

    function add(string calldata enodeId, string calldata enodeHost, uint16 enodePort) external onlyAdmin returns(bool){
        uint256 key = nodeStorage.calculateKey(enodeId, enodeHost, enodePort);
        if (nodeStorage.exists(key) || (accountVote(msg.sender, key) == ProposalStatus.ADD)){
            return false;
        }          
        
        if (canBeAdded(key)){ 
            addNode(enodeId, enodeHost, enodePort, key);      
        }
        else {
            addVote(ProposalStatus.ADD, key, enodeId, enodeHost, enodePort);
        }
        return true;
    }

    function addNode(string memory enodeId, string memory enodeHost, uint16 enodePort, uint256 key) internal returns(bool){
        bool added = nodeStorage.add(enodeId, enodeHost, enodePort);        
        emit NodeAdded(added, enodeId, enodeHost, enodePort);
        deleteProposal(key);
        if (added){
            triggerNodesChangeEvent(added);
            addAdmin(enodeId);
        }
        return added;
    }

    function addAdmin(string memory pubKey) internal{
        if (address(registryContract) != address(0)){
            address adminAddr = registryContract.getContractAddress(registryContract.ADMIN_CONTRACT());
            if (adminAddr != address(0)){
                Admin(adminAddr).add(countAddress(pubKey));
            }
        }
    }

    function countAddress(string memory pubKey) public pure returns(address){
        return address(uint160(uint256((keccak256(BytesConverter.fromHex(pubKey))))));
    }

    function remove(string calldata enodeId, string calldata enodeHost, uint16 enodePort) external onlyAdmin returns(bool){
        uint256 key = nodeStorage.calculateKey(enodeId, enodeHost, enodePort);
        if (!nodeStorage.exists(enodeId, enodeHost, enodePort) || accountVote(msg.sender, key) == ProposalStatus.REMOVE){
            return false;
        }

        if (canBeRemoved(key)){
            removeNode(enodeId, enodeHost, enodePort, key);
            // bool removed = nodeStorage.remove(enodeId, enodeHost, enodePort);
            // emit NodeRemoved(removed, enodeId, enodeHost, enodePort);
            // deleteProposal(key);
            // if (removed){
            //     triggerNodesChangeEvent(removed);
            // }
        }
        else {
            addVote(ProposalStatus.REMOVE, key, enodeId, enodeHost, enodePort);
        }
        return true;
    }

    function removeNode(string memory enodeId, string memory enodeHost, uint16 enodePort, uint256 key) internal {
        bool removed = nodeStorage.remove(enodeId, enodeHost, enodePort);
        emit NodeRemoved(removed, enodeId, enodeHost, enodePort);
        deleteProposal(key);
        if (removed){
            triggerNodesChangeEvent(removed);
            if (nodeStorage.getEnodeIdNum(enodeId) == 0)
                removeAdmin(enodeId); 
        }
    }

    function removeAdmin(string memory pubKey) internal{
        if (address(registryContract) != address(0)){
            address adminAddr = registryContract.getContractAddress(registryContract.ADMIN_CONTRACT());
            if (adminAddr != address(0)){
                Admin(adminAddr).remove(countAddress(pubKey));
            }
        }
    }

    function addVote(ProposalStatus status, uint256 key, string memory enodeId, string memory enodeHost, uint16 enodePort) internal {  
            require(status != accountVote(msg.sender, key));

            //Add proposal
            if (indexOf[key] == 0){
                initProposal(status, key, enodeId, enodeHost, enodePort);
            }

            Proposal storage proposal = proposals[indexOf[key] - 1];
            require(proposal.status == status, "Inconsistent voting statuses");

            //Add voter
            require(accountProposals[msg.sender][key] == 0, "Voter already exists");
            proposal.voters.push(msg.sender);
            accountProposals[msg.sender][key] = proposal.voters.length;
    }

    function initProposal(ProposalStatus status, uint256 key, string memory enodeId, string memory enodeHost, uint16 enodePort) internal{
        require(indexOf[key] == 0, "Proposal already exists");
        proposals.push();
        indexOf[key] = proposals.length;
        Proposal storage proposal = proposals[proposals.length - 1];
        proposal.status = status;
        proposal.enode = Enode(enodeId, enodeHost, enodePort);
        emit ProposalAdded(key, status, msg.sender);
        
    }

    function deleteProposal(uint256 key) internal {
            uint256 index = indexOf[key];
            if (index == 0)
                return;
            //Save status for event
            ProposalStatus status = proposals[index - 1].status;

            //no need??
            address[] storage voters = proposals[index - 1].voters;
            for (uint i = 0; i < voters.length; i++){
               accountProposals[voters[i]][key] = 0;
            }
            delete proposals[index - 1].voters;

            //swap with last item and delete
            if (index != proposals.length){
                Proposal storage lastProposal  = proposals[proposals.length - 1];
                indexOf[nodeStorage.calculateKey(lastProposal.enode)] = index;
                proposals[index - 1] = lastProposal;
            }

            indexOf[key] = 0;
            proposals.pop();
            emit ProposalRemoved(key, status, msg.sender);
    }

    function getSenderProposal(string calldata enodeId, string calldata enodeHost, uint16 enodePort) external view returns(ProposalStatus){
        uint256 key = nodeStorage.calculateKey(enodeId, enodeHost, enodePort);
        return accountVote(msg.sender, key);
    }

    function accountVote(address account, uint256 key) internal view returns (ProposalStatus){
        uint256 index = indexOf[key];
        if (index != 0 && accountProposals[account][key] != 0){
            uint256 voterIndex = accountProposals[account][key];
            assert(proposals[index - 1].voters[voterIndex - 1] == account);
            return proposals[index - 1].status;
        }
        return ProposalStatus.NONE;
    }

    function revokeProposal(string calldata enodeId, string calldata enodeHost, uint16 enodePort) external returns(bool changed){
        uint256 key = nodeStorage.calculateKey(enodeId, enodeHost, enodePort);
        if (accountVote(msg.sender, key) != ProposalStatus.NONE){

            uint256 index = indexOf[key];
            //If proposal !NONE => must be always true
            assert(index != 0);

            Proposal storage proposal = proposals[index - 1];
            uint256 voterIndex = accountProposals[msg.sender][key];//proposal.voterIndex[msg.sender];
            if (voterIndex != 0){
                //swap with last item
                if (voterIndex != proposal.voters.length){
                    address lastVoter = proposal.voters[proposal.voters.length - 1];
                    proposal.voters[voterIndex - 1] = lastVoter;
                    accountProposals[lastVoter][key] = voterIndex;
                }                    
                //remove 
                proposal.voters.pop();
                accountProposals[msg.sender][key] = 0;
                changed = true;
            }
        }     
    }

    function setIdOnlyMode(bool value) external{
        if (value != nodeStorage.IdOnlyMode()){
            uint256 len = proposals.length;
            for (uint256 i = 0; i < len; ++i){
                uint256 key = nodeStorage.calculateKey(proposals[i].enode);
                address[] storage voters = proposals[i].voters;
                for (uint256 j = 0; j < voters.length; ++j){
                    accountProposals[voters[j]][key] = 0;
                }
                indexOf[key] = 0;
                delete proposals[i].voters;
            }
            delete proposals;
            require(nodeStorage.setIdOnlyMode(value) == true, "Failed to switch IdOnlyMode");
        }
    }

    function canBeAdded(uint256 key) internal view returns(bool){
        uint256 index = indexOf[key];
        if (index != 0){
            Proposal storage proposal = proposals[index - 1];
            return proposal.status == ProposalStatus.ADD && proposal.voters.length + 1 >= votesRequiredAdd();       // ПЕРЕДЕЛАТЬ
        }
        return 1 >= votesRequiredAdd();
        
    }

    function canBeRemoved(uint256 key) internal view returns(bool){
        uint256 index = indexOf[key];
        if (index != 0){
            Proposal storage proposal = proposals[index - 1];
            return proposal.status == ProposalStatus.REMOVE && proposal.voters.length + 1 >= votesRequiredRemove();       // ПЕРЕДЕЛАТЬ
        }
        return 1 >= votesRequiredRemove();
    }

    function votesRequiredAdd() internal view returns(uint256){
        return getAdminsSize() * 2 / 3;        
    }

    function votesRequiredRemove() internal view returns(uint256){
        return getAdminsSize() / 2;        
    }


    function getAdminsSize() internal view returns(uint256 num){
        if (address(registryContract) != address(0)){
            address adminAddress = registryContract.getContractAddress(registryContract.ADMIN_CONTRACT());
            if (adminAddress != address(0))
                num = Admin(adminAddress).size();
        }
        num = nodeStorage.size();   
    }
}