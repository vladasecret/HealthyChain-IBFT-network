package org.web3j.contractWrapper.generated;

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
    public static final String BINARY = "608060405234801561001057600080fd5b506104ab806100206000396000f3fe608060405234801561001057600080fd5b5060043610610052577c01000000000000000000000000000000000000000000000000000000006000350463936421d581146100575780639d66de621461007e575b600080fd5b61006a6100653660046102e2565b610093565b604051901515815260200160405180910390f35b61009161008c366004610397565b610273565b005b60008054600160a060020a03166100ac57506001610268565b60008054604080517f369d40870000000000000000000000000000000000000000000000000000000081529051600160a060020a0390921691630d2020dd91839163369d4087916004808201926020929091908290030181865afa158015610118573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061013c91906103bb565b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161017691815260200190565b602060405180830381865afa158015610193573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906101b791906103d4565b9050600160a060020a03811615610262576040517f936421d5000000000000000000000000000000000000000000000000000000008152600160a060020a0382169063936421d590610219908c908c908c908c908c908c908c906004016103f1565b602060405180830381865afa158015610236573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061025a9190610453565b915050610268565b60019150505b979650505050505050565b600154600160a060020a03161561029b57600154600160a060020a0316331461029b57600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600160a060020a03811681146102df57600080fd5b50565b600080600080600080600060c0888a0312156102fd57600080fd5b8735610308816102ca565b96506020880135610318816102ca565b955060408801359450606088013593506080880135925060a088013567ffffffffffffffff8082111561034a57600080fd5b818a0191508a601f83011261035e57600080fd5b81358181111561036d57600080fd5b8b602082850101111561037f57600080fd5b60208301945080935050505092959891949750929550565b6000602082840312156103a957600080fd5b81356103b4816102ca565b9392505050565b6000602082840312156103cd57600080fd5b5051919050565b6000602082840312156103e657600080fd5b81516103b4816102ca565b6000600160a060020a03808a16835280891660208401525086604083015285606083015284608083015260c060a08301528260c0830152828460e0840137600060e0848401015260e0601f19601f850116830101905098975050505050505050565b60006020828403121561046557600080fd5b815180151581146103b457600080fdfea2646970667358221220470676bdc4bf8a3accd0e878d6dd4b1b860545b47dfe5e33e20c56dba60aef9664736f6c634300080c0033";

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

    @Deprecated
    public static RemoteCall<AccountIngress> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccountIngress.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<AccountIngress> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AccountIngress.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AccountIngress> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccountIngress.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
