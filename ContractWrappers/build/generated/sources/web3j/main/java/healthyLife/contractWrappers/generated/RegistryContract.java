package healthyLife.contractWrappers.generated;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
public class RegistryContract extends Contract {
    public static final String BINARY = "60806040527f6e6f6465436f6e74726f6c6c65720000000000000000000000000000000000006000557f6163636f756e74436f6e74726f6c6c65720000000000000000000000000000006001557f61646d696e73000000000000000000000000000000000000000000000000000060025534801561007c57600080fd5b5060038054600160a060020a03191633179055610aba8061009e6000396000f3fe608060405234801561001057600080fd5b50600436106100c6576000357c0100000000000000000000000000000000000000000000000000000000900480636abffc8f1161008e5780636abffc8f14610153578063a43e04d81461015c578063de8fa4311461016f578063e001f84114610177578063f35b2ad31461018a578063fe9fbb801461019d57600080fd5b80630d2020dd146100cb57806310d9042e146100fb5780631e7c27cb1461011057806324d7806c14610127578063369d40871461014a575b600080fd5b6100de6100d9366004610869565b6101b0565b604051600160a060020a0390911681526020015b60405180910390f35b6101036101f7565b6040516100f29190610882565b61011960025481565b6040519081526020016100f2565b61013a6101353660046108e6565b610259565b60405190151581526020016100f2565b61011960015481565b61011960005481565b61013a61016a366004610869565b610326565b600554610119565b61013a610185366004610908565b6105c9565b61013a610198366004610869565b6107a8565b61013a6101ab3660046108e6565b6107e8565b6000816101db5760405160e560020a62461bcd0281526004016101d290610934565b60405180910390fd5b50600090815260046020526040902054600160a060020a031690565b6060600580548060200260200160405190810160405280929190818152602001828054801561024f57602002820191906000526020600020905b8154600160a060020a03168152600190910190602001808311610231575b5050505050905090565b600254600090815260046020526040812054600160a060020a031661028057506001919050565b6002546000908152600460208190526040918290205491517ffe9fbb80000000000000000000000000000000000000000000000000000000008152600160a060020a038581169282019290925291169063fe9fbb80906024015b602060405180830381865afa1580156102f7573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061031b9190610969565b92915050565b919050565b600354600090600160a060020a03161561036757600354600160a060020a031633146103675760405160e560020a62461bcd0281526004016101d29061098b565b816103875760405160e560020a62461bcd0281526004016101d290610934565b6005546104255760405160e560020a62461bcd02815260206004820152604760248201527f4d7573742068617665206174206c65617374206f6e652072656769737465726560448201527f6420636f6e747261637420746f20657865637574652064656c657465206f706560648201527f726174696f6e2e00000000000000000000000000000000000000000000000000608482015260a4016101d2565b600082815260046020908152604080832054600160a060020a031683526006909152902054801580159061045b57506005548111155b156105c057600554811461050a57600580546000919061047d906001906109e8565b8154811061048d5761048d610a26565b600091825260209091200154600160a060020a031690508060056104b26001856109e8565b815481106104c2576104c2610a26565b6000918252602080832091909101805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039485161790559290911681526006909152604090208190555b600580548061051b5761051b610a55565b600082815260208082206000199084018101805473ffffffffffffffffffffffffffffffffffffffff199081169091559301909355858152600480845260408083208054600160a060020a03168452600686528184208490558884529185528154909316905581519081529182018590527fe3d908a1f6d2467f8e7c8198f30125843211345eedb763beb4cdfb7fe728a5af910160405180910390a150600192915050565b50600092915050565b600354600090600160a060020a03161561060a57600354600160a060020a0316331461060a5760405160e560020a62461bcd0281526004016101d29061098b565b8261062a5760405160e560020a62461bcd0281526004016101d290610934565b600160a060020a0382166106a95760405160e560020a62461bcd02815260206004820152602260248201527f436f6e74726163742061646472657373206d757374206e6f74206265207a657260448201527f6f2e00000000000000000000000000000000000000000000000000000000000060648201526084016101d2565b600160a060020a03821660009081526006602052604090205461072e57600580546001810182557f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0385169081179091559054600091825260066020526040909120555b600083815260046020908152604091829020805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03861690811790915582519081529081018590527fe3d908a1f6d2467f8e7c8198f30125843211345eedb763beb4cdfb7fe728a5af910160405180910390a150600192915050565b6000816107ca5760405160e560020a62461bcd0281526004016101d290610934565b50600090815260046020526040902054600160a060020a0316151590565b60008054815260046020526040812054600160a060020a031661080d57506001919050565b600080548152600460208190526040918290205491517fc3c5a547000000000000000000000000000000000000000000000000000000008152600160a060020a038581169282019290925291169063c3c5a547906024016102da565b60006020828403121561087b57600080fd5b5035919050565b6020808252825182820181905260009190848201906040850190845b818110156108c3578351600160a060020a03168352928401929184019160010161089e565b50909695505050505050565b8035600160a060020a038116811461032157600080fd5b6000602082840312156108f857600080fd5b610901826108cf565b9392505050565b6000806040838503121561091b57600080fd5b8235915061092b602084016108cf565b90509250929050565b6020808252818101527f436f6e7472616374206e616d65206d757374206e6f7420626520656d7074792e604082015260600190565b60006020828403121561097b57600080fd5b8151801515811461090157600080fd5b6020808252602e908201527f5468652073656e646572206d757374206265207468652063726561746f72206f60408201527f662074686520636f6e7472616374000000000000000000000000000000000000606082015260800190565b600082821015610a21577f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b500390565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea26469706673582212200b6ec6cff118d5c46784ce98e1b745a73910fa85feba58b47a03ed867deebe5b64736f6c634300080c0033";

    public static final String FUNC_ACCOUNT_CONTROLLER_CONTRACT = "ACCOUNT_CONTROLLER_CONTRACT";

    public static final String FUNC_ADMIN_CONTRACT = "ADMIN_CONTRACT";

    public static final String FUNC_NODE_CONTROLLER_CONTRACT = "NODE_CONTROLLER_CONTRACT";

    public static final String FUNC_GETALLCONTRACTKEYS = "getAllContractKeys";

    public static final String FUNC_GETCONTRACTADDRESS = "getContractAddress";

    public static final String FUNC_GETSIZE = "getSize";

    public static final String FUNC_HASCONTRACTADDRESS = "hasContractAddress";

    public static final String FUNC_ISADMIN = "isAdmin";

    public static final String FUNC_ISAUTHORIZED = "isAuthorized";

    public static final String FUNC_REMOVECONTRACT = "removeContract";

    public static final String FUNC_SETCONTRACTADDRESS = "setContractAddress";

    public static final Event REGISTRYUPDATED_EVENT = new Event("RegistryUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
    ;

    @Deprecated
    protected RegistryContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RegistryContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RegistryContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RegistryContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<RegistryUpdatedEventResponse> getRegistryUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTRYUPDATED_EVENT, transactionReceipt);
        ArrayList<RegistryUpdatedEventResponse> responses = new ArrayList<RegistryUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RegistryUpdatedEventResponse typedResponse = new RegistryUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.contractAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.contractName = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RegistryUpdatedEventResponse> registryUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RegistryUpdatedEventResponse>() {
            @Override
            public RegistryUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REGISTRYUPDATED_EVENT, log);
                RegistryUpdatedEventResponse typedResponse = new RegistryUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.contractAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.contractName = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RegistryUpdatedEventResponse> registryUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REGISTRYUPDATED_EVENT));
        return registryUpdatedEventFlowable(filter);
    }

    public RemoteFunctionCall<byte[]> ACCOUNT_CONTROLLER_CONTRACT() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ACCOUNT_CONTROLLER_CONTRACT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> ADMIN_CONTRACT() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ADMIN_CONTRACT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> NODE_CONTROLLER_CONTRACT() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NODE_CONTROLLER_CONTRACT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<List> getAllContractKeys() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETALLCONTRACTKEYS, 
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

    public RemoteFunctionCall<String> getContractAddress(byte[] name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCONTRACTADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getSize() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> hasContractAddress(byte[] name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HASCONTRACTADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isAdmin(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isAuthorized(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAUTHORIZED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeContract(byte[] _name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVECONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setContractAddress(byte[] name, String addr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETCONTRACTADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.Address(160, addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static RegistryContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RegistryContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RegistryContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RegistryContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RegistryContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RegistryContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RegistryContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RegistryContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RegistryContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RegistryContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<RegistryContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RegistryContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RegistryContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RegistryContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RegistryContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RegistryContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RegistryUpdatedEventResponse extends BaseEventResponse {
        public String contractAddress;

        public byte[] contractName;
    }
}
