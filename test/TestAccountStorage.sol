pragma solidity ^0.8.12;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/Account/AccountStorage.sol";
import "../contracts/User/UserContract.sol";
import "../contracts/RegistryContract.sol";

contract TestAccountStorage is AccountStorage{
    //NodeStorage nodeStorage = NodeStorage(DeployedAddresses.NodeStorage());
    //AccountStorage accountStorage = new AccountStorage();

    address[] accounts;

    constructor(){
        accounts.push(address(0x68c83A0c875Ec292CdDe5207237f0cd0E693A228));
        accounts.push(address(0x3dcc953793207F3a9B1dCe8AE450935230588182));
        accounts.push(address(0xB62917FC06222773aFed06B572C6C9e0647F5afd));
        accounts.push(address(0x6852C92A0a2e384f221022915C022F79aAb501af));
        accounts.push(address(0x7bb37A4AE82F8b4796982f75eae769B7828Fb03D));
                            
    }

    function userContractMock() internal returns(UserContract){
        return new UserContract(address(0), RegistryContract(address(0)));
    }

    function testEmptySize() public {
        Assert.equal(size(), 0, "Storage must be empty");
    }

    function testAddSuccess() public {
        for (uint i = 0; i < accounts.length; i++){
            bool res = add(accounts[i], UserClass(i % 3), userContractMock());
            assert(res);
        }
    }

    function testSize() public {
        Assert.equal(size(), accounts.length, "Storage mustn't be empty");
    }
    

    function testAddFailture() public {
        bool res = add(accounts[0], UserClass(1), userContractMock());
        Assert.equal(res, false, "Double add should not be successful");
    }

    function testExistsSuccess() public{
        Assert.equal(exists(accounts[0]), true, "accounts[0] must exist");
    }


    function testRemoveLastItem() public{
        uint256 size = size();
        bool res = remove(accounts[4]);
        Assert.equal(res, true, "Remove failture");
        Assert.equal(this.size(), size - 1, "");
        Assert.equal(exists(accounts[4]), false, "element must be removed");
    }

    function testRemoveItem() public{
        bool res = remove(accounts[0]);
        Assert.equal(res, true, "");
        Assert.equal(exists(accounts[0]), false, "");
        Assert.equal(exists(accounts[3]), true, "");
        Assert.equal(indexOf[accounts[3]], 1, "");
    }

    function TestUserClass() public {
        for (uint i = 0; i < accounts.length; i++){
            if (exists(accounts[i]))
            assert(userClass(accounts[i]) == UserClass(i % 3));
        }
    }
}