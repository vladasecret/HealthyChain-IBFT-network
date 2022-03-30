var RegistryContract = artifacts.require("RegistryContract");

module.exports = function(deployer) {
    deployer.deploy(RegistryContract);

  };
