var EnodeLib = artifacts.require("EnodeLib");
var NodeStorage = artifacts.require("NodeStorage");

module.exports = function(deployer) {
    deployer.deploy(EnodeLib);
    deployer.link(EnodeLib, NodeStorage);
    deployer.deploy(NodeStorage);
  };
