package healthyLife.contractWrappers.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class AdminStorage extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060028054600160a060020a03191633179055610663806100326000396000f3fe608060405234801561001057600080fd5b506004361061007e577c010000000000000000000000000000000000000000000000000000000060003504630a3b0a4f811461008357806329092d0e146100985780632d883a73146100ab578063949d225d146100db578063c5eff3d0146100ec578063f6a3d24e14610101575b600080fd5b61009661009136600461049e565b61013c565b005b6100966100a636600461049e565b610213565b6100be6100b93660046104ce565b61038b565b604051600160a060020a0390911681526020015b60405180910390f35b6000546040519081526020016100d2565b6100f461043c565b6040516100d291906104e7565b61012c61010f36600461049e565b600160a060020a0316600090815260016020526040902054151590565b60405190151581526020016100d2565b600254600160a060020a03163314610189576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161018090610534565b60405180910390fd5b600160a060020a0381166000908152600160205260409020546102105760008054600180820183557f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563909101805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03851690811790915582549083526020919091526040909120555b50565b600254600160a060020a03163314610257576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161018090610534565b600160a060020a0381166000908152600160205260409020541561021057600160a060020a0381166000908152600160205260408120549054811461032e576000805481906102a890600190610591565b815481106102b8576102b86105cf565b6000918252602080832090910154600160a060020a03168083526001918290526040832085905592508291906102ee9085610591565b815481106102fe576102fe6105cf565b9060005260206000200160006101000a815481600160a060020a030219169083600160a060020a03160217905550505b600160a060020a0382166000908152600160205260408120819055805480610358576103586105fe565b6000828152602090208101600019908101805473ffffffffffffffffffffffffffffffffffffffff191690550190555050565b6000808211801561039e57506000548211155b610404576040517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152600d60248201527f4f7574206f6620626f756e6473000000000000000000000000000000000000006044820152606401610180565b6000610411600184610591565b81548110610421576104216105cf565b600091825260209091200154600160a060020a031692915050565b6060600080548060200260200160405190810160405280929190818152602001828054801561049457602002820191906000526020600020905b8154600160a060020a03168152600190910190602001808311610476575b5050505050905090565b6000602082840312156104b057600080fd5b8135600160a060020a03811681146104c757600080fd5b9392505050565b6000602082840312156104e057600080fd5b5035919050565b6020808252825182820181905260009190848201906040850190845b81811015610528578351600160a060020a031683529284019291840191600101610503565b50909695505050505050565b6020808252602c908201527f5468652073656e646572206d75737420626520746865206f776e6572206f662060408201527f74686520636f6e74726163740000000000000000000000000000000000000000606082015260800190565b6000828210156105ca577f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b500390565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea2646970667358221220edafaa4db5323bd14d372e79be06d1b6e489e39d344c78d8d3ccd5a8d301d14d64736f6c634300080c0033";

    public static final String FUNC_ADD = "add";

    public static final String FUNC_EXISTS = "exists";

    public static final String FUNC_GETALLOWLIST = "getAllowlist";

    public static final String FUNC_GETBYINDEX = "getByIndex";

    public static final String FUNC_REMOVE = "remove";

    public static final String FUNC_SIZE = "size";

    @Deprecated
    protected AdminStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AdminStorage(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AdminStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AdminStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> add(String _address) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> exists(String _address) {
        final Function function = new Function(FUNC_EXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<List> getAllowlist() {
        final Function function = new Function(FUNC_GETALLOWLIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> remove(String _address) {
        final Function function = new Function(
                FUNC_REMOVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> size() {
        final Function function = new Function(FUNC_SIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static AdminStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AdminStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AdminStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AdminStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AdminStorage load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AdminStorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AdminStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AdminStorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AdminStorage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AdminStorage.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<AdminStorage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AdminStorage.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AdminStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AdminStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AdminStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AdminStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
