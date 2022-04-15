package healthyLife.contractWrappers.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class AccountIngress extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060018054600160a060020a03191633179055610532806100326000396000f3fe608060405234801561001057600080fd5b5060043610610052577c01000000000000000000000000000000000000000000000000000000006000350463936421d581146100575780639d66de621461007e575b600080fd5b61006a610065366004610369565b610093565b604051901515815260200160405180910390f35b61009161008c36600461041e565b610273565b005b60008054600160a060020a03166100ac57506001610268565b60008054604080517f369d40870000000000000000000000000000000000000000000000000000000081529051600160a060020a0390921691630d2020dd91839163369d4087916004808201926020929091908290030181865afa158015610118573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061013c9190610442565b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161017691815260200190565b602060405180830381865afa158015610193573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906101b7919061045b565b9050600160a060020a03811615610262576040517f936421d5000000000000000000000000000000000000000000000000000000008152600160a060020a0382169063936421d590610219908c908c908c908c908c908c908c90600401610478565b602060405180830381865afa158015610236573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061025a91906104da565b915050610268565b60019150505b979650505050505050565b600154600160a060020a03161561032257600154600160a060020a03163314610322576040517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152602a60248201527f5468652073656e646572206d7573742062652063726561746f72206f6620746860448201527f6520636f6e747261637400000000000000000000000000000000000000000000606482015260840160405180910390fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600160a060020a038116811461036657600080fd5b50565b600080600080600080600060c0888a03121561038457600080fd5b873561038f81610351565b9650602088013561039f81610351565b955060408801359450606088013593506080880135925060a088013567ffffffffffffffff808211156103d157600080fd5b818a0191508a601f8301126103e557600080fd5b8135818111156103f457600080fd5b8b602082850101111561040657600080fd5b60208301945080935050505092959891949750929550565b60006020828403121561043057600080fd5b813561043b81610351565b9392505050565b60006020828403121561045457600080fd5b5051919050565b60006020828403121561046d57600080fd5b815161043b81610351565b6000600160a060020a03808a16835280891660208401525086604083015285606083015284608083015260c060a08301528260c0830152828460e0840137600060e0848401015260e0601f19601f850116830101905098975050505050505050565b6000602082840312156104ec57600080fd5b8151801515811461043b57600080fdfea26469706673582212203ab6048216d52e7b0472fd2782dd858b04562970b0314e2f98887f7f04dd3f5764736f6c634300080c0033";

    public static final String FUNC_SETREGISTYCONTRACT = "setRegistyContract";

    public static final String FUNC_TRANSACTIONALLOWED = "transactionAllowed";

    @Deprecated
    protected AccountIngress(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AccountIngress(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AccountIngress(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AccountIngress(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> setRegistyContract(String _address) {
        final Function function = new Function(
                FUNC_SETREGISTYCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> transactionAllowed(String sender, String target, BigInteger value, BigInteger gasPrice, BigInteger gasLimit, byte[] payload) {
        final Function function = new Function(FUNC_TRANSACTIONALLOWED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, target), 
                new org.web3j.abi.datatypes.generated.Uint256(value), 
                new org.web3j.abi.datatypes.generated.Uint256(gasPrice), 
                new org.web3j.abi.datatypes.generated.Uint256(gasLimit), 
                new org.web3j.abi.datatypes.DynamicBytes(payload)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static AccountIngress load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccountIngress(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AccountIngress load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccountIngress(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AccountIngress load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AccountIngress(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AccountIngress load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AccountIngress(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AccountIngress> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AccountIngress.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<AccountIngress> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AccountIngress.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AccountIngress> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccountIngress.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AccountIngress> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccountIngress.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
