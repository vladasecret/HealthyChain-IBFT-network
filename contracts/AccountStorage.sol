pragma solidity ^0.8.12;

import "./AccountContract.sol";

enum AccountClass {PATIENT, DOCTOR, PROVIDER}

contract AccountStorage{

    struct AccountInfo{
        address accountAddr;
        AccountClass class;
        AccountContract accContract;
    }

    AccountInfo[] allowlist;
    mapping(address => uint256) indexOf;

    function size() public view returns (uint256) {
        return allowlist.length;
    }

    function exists(address account) internal view returns (bool) {
        return indexOf[account] != 0;
    }

    function add(address account, AccountClass class, AccountContract accContract) internal returns (bool) {
        if (indexOf[account] == 0) {
            indexOf[account] = allowlist.length + 1;
            allowlist.push(AccountInfo(account, class, accContract));
            return true;
        }
        return false;
    }

    function remove(address account) internal returns (bool) {
        uint256 index = indexOf[account];
        if (index > 0 && index <= allowlist.length) { //1-based indexing
            
            if (index != allowlist.length) {
                AccountInfo memory lastAccount = allowlist[allowlist.length - 1];
                allowlist[index - 1] = lastAccount;
                indexOf[lastAccount.accountAddr] = index;
            }
            allowlist.pop();
            indexOf[account] = 0;
            return true;
        }
        return false;
    }

    function accountClass(address _address) view public returns(AccountClass){
        uint index = indexOf[_address];
        require(index > 0, "Account not registered");
        return allowlist[index - 1].class;
    }

    function getAccountContract(address _address) view public returns(AccountContract){
        return allowlist[indexOf[_address]].accContract;
    }
}