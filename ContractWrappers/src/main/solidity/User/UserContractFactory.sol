//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.0;

import "./UserContract.sol";
import "./PatientContract.sol";
import "./DoctorContract.sol";

import "../Account/IAccountStorage.sol";
import "./DoctorContract.sol";
import "./IUserContractFactory.sol";

contract UserContractFactory is IUserContractFactory {
    address registryContractAddress;
    constructor(address _registryContractAddress){
        registryContractAddress = _registryContractAddress;
    }

    function create(address owner, UserClass userClass) external returns(address){
        if (userClass == UserClass.PATIENT)
            return address(new PatientContract(owner, registryContractAddress));
        if (userClass == UserClass.DOCTOR)
            return address(new DoctorContract(owner, registryContractAddress));
        return address(new UserContract(owner, registryContractAddress));
    }
}
