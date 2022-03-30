pragma solidity ^0.8.12;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/Node/NodeStorage.sol";

contract TestNodeStorage {
    //NodeStorage nodeStorage = NodeStorage(DeployedAddresses.NodeStorage());
    NodeStorage nodeStorage = new NodeStorage();

    string[] enodes;

    constructor(){
        enodes.push("0x93df0dab486fc77ff7285f1d3fed4992ce96631af1c66f338acab9bb57deb3cdb2a2e6228f684b3058f8b214a8682a485e45c4c7f4047bd9f4a1e39d33ff47f3");
        enodes.push("0x93df0dab486fc77ff7285f1d3fed4992ce96631af1c66f338acab9bb57deb3cdb2a2e6228f684b3058f8b214a8682a485e45c4c7f4047bd9f4a1e39d33ff47f9");
        enodes.push("0x2b3b8f4bc24db317122711da044f4f6998aad429e31942fef51d1a5da2be59fa98a8a96e0561a910cbc9633975679594b4e8c0feb29bb86dca0b59af5438d3bb");
        enodes.push("0xa8cbd41e9f3235e01a95971636507ff8e4f18321fbff525f3b7244129da524c7703222d464c8b23c69ae11932e97d998f2943a5c6c175551743476b64ee36b78");
        enodes.push("0xcb38875a0712bf59e8a96f6e3bb84b35b877b89880c043d248e3f838f9497e1fab229150bd461cb12478afd8e919e74de497dabd47b509a8c1f2c655faa0b548");
    }

    function testEmptySize() public {
        Assert.equal(nodeStorage.size(), 0, "Storage must be empty");
    }

    function testAddSuccess() public {
        for (uint i = 0; i < enodes.length; i++){
            bool res = nodeStorage.add(enodes[i], "", 0);
            assert(res);
        }
    }
    
    function testSize() public {
        Assert.equal(nodeStorage.size(), enodes.length, "Storage mustn't be empty");
    }

    function testAddFailture() public {
        bool res = nodeStorage.add(enodes[1], "", 0);
        Assert.equal(res, false, "Double add should not be successful");
    }

    function testExistsSuccess() public{
        Assert.equal(nodeStorage.exists(enodes[0], "", 0), true, "enodes[0] must exist");
    }

    function testIdOnlyMode() public{
        Assert.equal(true, nodeStorage.exists(enodes[0], "", 0), "enodes[0] must exist");
        bool res = nodeStorage.add(enodes[0], "127.0.0.1", 2020);
        Assert.equal(res, false, "enodes[0] must exist");

        uint256 size = nodeStorage.size();

        nodeStorage.setIdOnlyMode(false);
        Assert.equal(true, nodeStorage.exists(enodes[0], "", 0), "enodes[0] does not exist");
        Assert.equal(false, nodeStorage.exists(enodes[0], "127.0.0.1", 2020), "(enodes[0], \"127.0.0.1\", 2020) mustn't exists");
        res = nodeStorage.add(enodes[0], "127.0.0.1", 2020);
        Assert.equal(res, true, "enodes[0]");
        Assert.equal(nodeStorage.getEnodeIdNum(enodes[0]), 2, "Enode must have 2 item in storage");
        Assert.notEqual(nodeStorage.getIndexOf(enodes[0], "127.0.0.1", 2020), nodeStorage.getIndexOf(enodes[0], "", 0), "Elements must have different indexes");
        Assert.equal(size + 1, nodeStorage.size(), "");
    }

    function testRemoveLastItem() public{
        uint256 size = nodeStorage.size();
        (string memory enodeId, string memory enodeHost, uint16 enodePort) = nodeStorage.getByIndex(size);
        bool res = nodeStorage.remove(enodeId, enodeHost, enodePort);
        Assert.equal(res, true, "Remove failture");
        Assert.equal(nodeStorage.size(), size - 1, "");
        Assert.equal(nodeStorage.exists(enodeId, enodeHost, enodePort), false, "element must be removed");
    }

    function testRemoveItem() public{
        uint256 size = nodeStorage.size();
        (string memory lastId, string memory lastHost, uint16 lastPort) = nodeStorage.getByIndex(size);        
        (string memory id, string memory host, uint16 port) = nodeStorage.getByIndex(1);
        bool res = nodeStorage.remove(id, host, port);
        Assert.equal(res, true, "");
        Assert.equal(nodeStorage.exists(id, host, port), false, "");
        Assert.equal(nodeStorage.exists(lastId, lastHost, lastPort), true, "");
        Assert.equal(size - 1, nodeStorage.size(), "");
        (string memory checkId,,) = nodeStorage.getByIndex(1);
        Assert.equal(1, nodeStorage.getIndexOf(lastId, lastHost, lastPort), "Last item must be the fist");
        Assert.equal(checkId, lastId, "Last item must be the fist");
    }
    

    function testRemove() public {
        uint256 size = nodeStorage.size();
        for (uint256 i = size; i > 0; i--){
            (string memory enodeId, string memory enodeHost, uint16 port) = nodeStorage.getByIndex(i);
            nodeStorage.remove(enodeId, enodeHost, port);
        }
        Assert.equal(nodeStorage.size(), 0, "Node storage must be empty");
    }


}