package healthyLife.contractWrappers.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tuples.generated.Tuple2;
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
public class AddressContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061087d806100206000396000f3fe608060405234801561001057600080fd5b5060043610610073577c0100000000000000000000000000000000000000000000000000000000600035046323fc7ef38114610078578063775ce80b1461009e5780638e7e34d7146100d65780638f9e1a5d146100f6578063abe7aa3014610109575b600080fd5b61008b610086366004610589565b610146565b6040519081526020015b60405180910390f35b6100b16100ac366004610589565b610168565b60405173ffffffffffffffffffffffffffffffffffffffff9091168152602001610095565b6100e96100e43660046105e4565b6101ba565b6040516100959190610695565b61008b610104366004610589565b610330565b61011c610117366004610589565b610380565b6040805192835273ffffffffffffffffffffffffffffffffffffffff909116602083015201610095565b600082826040516101589291906106ea565b6040518091039020905092915050565b6000806101aa84848080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152506101ba92505050565b8051602090910120949350505050565b805160609082906101cd90600290610713565b156101d757600080fd5b600060028083516101e89190610740565b6101f29190610757565b67ffffffffffffffff81111561020a5761020a6105cb565b6040519080825280601f01601f191660200182016040528015610234576020820181803683370190505b50905060005b60028084516102499190610740565b6102539190610757565b811015610328576102a28361026983600261076b565b61027490600361078a565b81518110610284576102846107a2565b602001015160f860020a900460f860020a0260f860020a90046103ac565b6102bc846102b184600261076b565b61027490600261078a565b6102c79060106107bb565b6102d191906107e4565b60f860020a028282815181106102e9576102e96107a2565b60200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a90535061032181610809565b905061023a565b509392505050565b600061037183838080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152506101ba92505050565b80519060200120905092915050565b600080600084846040516103959291906106ea565b6040519081900390208493509150505b9250929050565b60007f300000000000000000000000000000000000000000000000000000000000000060f860020a8302600160f860020a0319161080159061041e57507f390000000000000000000000000000000000000000000000000000000000000060f860020a8302600160f860020a03191611155b156104345761042e603083610824565b92915050565b7f610000000000000000000000000000000000000000000000000000000000000060f860020a8302600160f860020a031916108015906104a457507f660000000000000000000000000000000000000000000000000000000000000060f860020a8302600160f860020a03191611155b156104c05760616104b683600a6107e4565b61042e9190610824565b7f410000000000000000000000000000000000000000000000000000000000000060f860020a8302600160f860020a0319161080159061053057507f460000000000000000000000000000000000000000000000000000000000000060f860020a8302600160f860020a03191611155b156105425760416104b683600a6107e4565b919050565b60008083601f84011261055957600080fd5b50813567ffffffffffffffff81111561057157600080fd5b6020830191508360208285010111156103a557600080fd5b6000806020838503121561059c57600080fd5b823567ffffffffffffffff8111156105b357600080fd5b6105bf85828601610547565b90969095509350505050565b60e060020a634e487b7102600052604160045260246000fd5b6000602082840312156105f657600080fd5b813567ffffffffffffffff8082111561060e57600080fd5b818401915084601f83011261062257600080fd5b813581811115610634576106346105cb565b604051601f8201601f19908116603f0116810190838211818310171561065c5761065c6105cb565b8160405282815287602084870101111561067557600080fd5b826020860160208301376000928101602001929092525095945050505050565b600060208083528351808285015260005b818110156106c2578581018301518582016040015282016106a6565b818111156106d4576000604083870101525b50601f01601f1916929092016040019392505050565b8183823760009101908152919050565b60e060020a634e487b7102600052601260045260246000fd5b600082610722576107226106fa565b500690565b60e060020a634e487b7102600052601160045260246000fd5b60008282101561075257610752610727565b500390565b600082610766576107666106fa565b500490565b600081600019048311821515161561078557610785610727565b500290565b6000821982111561079d5761079d610727565b500190565b60e060020a634e487b7102600052603260045260246000fd5b600060ff821660ff84168160ff04811182151516156107dc576107dc610727565b029392505050565b600060ff821660ff84168060ff0382111561080157610801610727565b019392505050565b600060001982141561081d5761081d610727565b5060010190565b600060ff821660ff84168082101561083e5761083e610727565b9003939250505056fea264697066735822122040c5eaabde4700eda8996c69c2ee7b2002f41f9eb8c982070810cf71b7da06a664736f6c634300080c0033";

    public static final String FUNC_countAddress = "countAddress";

    public static final String FUNC_FROMHEX = "fromHex";

    public static final String FUNC_keccak = "keccak";

    @Deprecated
    protected AddressContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AddressContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AddressContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AddressContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> countAddress(String pubKey) {
        final Function function = new Function(FUNC_countAddress, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(pubKey)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, String>> countAddress(byte[] pubKey) {
        final Function function = new Function(FUNC_countAddress, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(pubKey)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, String>>(function,
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<byte[]> fromHex(String self) {
        final Function function = new Function(FUNC_FROMHEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(self)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> keccak(byte[] pubKey) {
        final Function function = new Function(FUNC_keccak, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(pubKey)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> keccak(String pubKey) {
        final Function function = new Function(FUNC_keccak, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(pubKey)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    @Deprecated
    public static AddressContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AddressContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AddressContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AddressContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AddressContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AddressContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AddressContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AddressContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AddressContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AddressContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AddressContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AddressContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<AddressContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AddressContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AddressContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AddressContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
