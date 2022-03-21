var EnodeLib = artifacts.require("EnodeLib");
var NodeStorage = artifacts.require("NodeStorage");
var NodeController = artifacts.require("NodeController");

module.exports = function(deployer) {
//    deployer.deploy(NodeStorage);
    var address = '0x0000000000000000000000000000000000000000'
    deployer.deploy(NodeController, address);
  };
