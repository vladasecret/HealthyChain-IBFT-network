package org.web3j.contractWrapper.generated;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.0.
 */
@SuppressWarnings("rawtypes")
public class EnodeLib extends Contract {
    public static final String BINARY = "610550610053600b82828239805160001a607314610046577f4e487b7100000000000000000000000000000000000000000000000000000000600052600060045260246000fd5b30600052607381538281f3fe7300000000000000000000000000000000000000003014608060405260043610610072577c01000000000000000000000000000000000000000000000000000000006000350463116c85ae811461007757806370d418191461009c578063b736d41b146100af578063b8debb1b146100c2575b600080fd5b61008a610085366004610291565b6100d5565b60405190815260200160405180910390f35b61008a6100aa366004610316565b610125565b61008a6100bd36600461034b565b610156565b61008a6100d0366004610415565b610177565b600081156100ed576100e685610125565b905061011d565b848484604051602001610102939291906104c4565b60408051601f19818403018152919052805160209091012090505b949350505050565b600081604051602001610138919061050e565b60408051601f19818403018152919052805160209091012092915050565b6000610170836000015184602001518560400151856100d5565b9392505050565b600083838360405160200161018e939291906104c4565b60408051808303601f190181529190528051602090910120949350505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600082601f8301126101ee57600080fd5b813567ffffffffffffffff80821115610209576102096101ae565b604051601f8301601f19908116603f01168101908282118183101715610231576102316101ae565b8160405283815286602085880101111561024a57600080fd5b836020870160208301376000602085830101528094505050505092915050565b803561ffff8116811461027c57600080fd5b919050565b8035801515811461027c57600080fd5b600080600080608085870312156102a757600080fd5b843567ffffffffffffffff808211156102bf57600080fd5b6102cb888389016101dd565b955060208701359150808211156102e157600080fd5b506102ee878288016101dd565b9350506102fd6040860161026a565b915061030b60608601610281565b905092959194509250565b60006020828403121561032857600080fd5b813567ffffffffffffffff81111561033f57600080fd5b61011d848285016101dd565b6000806040838503121561035e57600080fd5b823567ffffffffffffffff8082111561037657600080fd5b908401906060828703121561038a57600080fd5b6040516060810181811083821117156103a5576103a56101ae565b6040528235828111156103b757600080fd5b6103c3888286016101dd565b8252506020830135828111156103d857600080fd5b6103e4888286016101dd565b6020830152506103f66040840161026a565b6040820152935061040c91505060208401610281565b90509250929050565b60008060006060848603121561042a57600080fd5b833567ffffffffffffffff8082111561044257600080fd5b61044e878388016101dd565b9450602086013591508082111561046457600080fd5b50610471868287016101dd565b9250506104806040850161026a565b90509250925092565b6000815160005b818110156104aa5760208185018101518683015201610490565b818111156104b9576000828601525b509290920192915050565b60006104d96104d38387610489565b85610489565b61ffff939093167e01000000000000000000000000000000000000000000000000000000000000028352505060020192915050565b6000610170828461048956fea2646970667358221220f127f79794db79478b420861ba0fc60d89f170c9547d09e9d3bc20a33f21954364736f6c634300080c0033";

    public static final String FUNC_calculateKey = "calculateKey";

    @Deprecated
    protected EnodeLib(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EnodeLib(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EnodeLib(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EnodeLib(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> calculateKey(String enodeId, String enodeHost, BigInteger enodePort, Boolean idOnlyMode) {
        final Function function = new Function(FUNC_calculateKey, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort), 
                new org.web3j.abi.datatypes.Bool(idOnlyMode)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> calculateKey(String enodeId) {
        final Function function = new Function(FUNC_calculateKey, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> calculateKey(Enode enode, Boolean idOnlyMode) {
        final Function function = new Function(FUNC_calculateKey, 
                Arrays.<Type>asList(enode, 
                new org.web3j.abi.datatypes.Bool(idOnlyMode)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> calculateKey(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(FUNC_calculateKey, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static EnodeLib load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EnodeLib(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EnodeLib load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EnodeLib(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EnodeLib load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EnodeLib(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EnodeLib load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EnodeLib(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EnodeLib> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EnodeLib.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EnodeLib> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EnodeLib.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<EnodeLib> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EnodeLib.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EnodeLib> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EnodeLib.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Enode extends DynamicStruct {
        public String enodeId;

        public String enodeHost;

        public BigInteger enodePort;

        public Enode(String enodeId, String enodeHost, BigInteger enodePort) {
            super(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                    new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                    new org.web3j.abi.datatypes.generated.Uint16(enodePort));
            this.enodeId = enodeId;
            this.enodeHost = enodeHost;
            this.enodePort = enodePort;
        }

        public Enode(Utf8String enodeId, Utf8String enodeHost, Uint16 enodePort) {
            super(enodeId, enodeHost, enodePort);
            this.enodeId = enodeId.getValue();
            this.enodeHost = enodeHost.getValue();
            this.enodePort = enodePort.getValue();
        }
    }
}
