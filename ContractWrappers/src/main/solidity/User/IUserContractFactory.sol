//SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.8.0;

import "./UserContract.sol";
import "./PatientContract.sol";
import "./DoctorContract.sol";
import "../Account/IAccountStorage.sol";

interface IUserContractFactory {
    function create(address owner, UserClass class) external returns(address);
}
