package org.web3j.contractWrapper.generated;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
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
public class Admin extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50604051610fb7380380610fb783398101604081905261002f9161011c565b60018054600160a060020a031916600160a060020a0383161790556040516100569061010f565b604051809103906000f080158015610072573d6000803e3d6000fd5b5060008054600160a060020a0319908116600160a060020a039390931692831790915560028054339216821790556040517f0a3b0a4f0000000000000000000000000000000000000000000000000000000081526004810191909152630a3b0a4f90602401600060405180830381600087803b1580156100f157600080fd5b505af1158015610105573d6000803e3d6000fd5b505050505061014c565b6106958061092283390190565b60006020828403121561012e57600080fd5b8151600160a060020a038116811461014557600080fd5b9392505050565b6107c78061015b6000396000f3fe608060405234801561001057600080fd5b50600436106100655760e060020a60003504630a3b0a4f811461006a5780632182f0501461007f57806329092d0e1461009f578063949d225d146100b25780639827caa7146100c8578063fe9fbb80146100db575b600080fd5b61007d6100783660046106b8565b6100fe565b005b600054604051600160a060020a0390911681526020015b60405180910390f35b61007d6100ad3660046106b8565b61030f565b6100ba6104e0565b604051908152602001610096565b61007d6100d63660046106b8565b610560565b6100ee6100e93660046106b8565b610612565b6040519015158152602001610096565b600154604080517f6abffc8f00000000000000000000000000000000000000000000000000000000815290513392600160a060020a031691630d2020dd918391636abffc8f9160048083019260209291908290030181865afa158015610168573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061018c91906106dc565b6040518263ffffffff1660e060020a0281526004016101ad91815260200190565b602060405180830381865afa1580156101ca573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906101ee91906106f5565b600160a060020a0316146102205760405160e560020a62461bcd02815260040161021790610712565b60405180910390fd5b80600160a060020a0381163b156102565760405160e560020a62461bcd0281526020600482015260006024820152604401610217565b6000546040517f0a3b0a4f000000000000000000000000000000000000000000000000000000008152600160a060020a03848116600483015290911690630a3b0a4f90602401600060405180830381600087803b1580156102b657600080fd5b505af11580156102ca573d6000803e3d6000fd5b5050604051600160a060020a03851681527f44d6d25963f097ad14f29f06854a01f575648a1ef82f30e562ccd3889717e3399250602001905060405180910390a15050565b600154604080517f6abffc8f00000000000000000000000000000000000000000000000000000000815290513392600160a060020a031691630d2020dd918391636abffc8f9160048083019260209291908290030181865afa158015610379573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061039d91906106dc565b6040518263ffffffff1660e060020a0281526004016103be91815260200190565b602060405180830381865afa1580156103db573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103ff91906106f5565b600160a060020a0316146104285760405160e560020a62461bcd02815260040161021790610712565b6000546040517f29092d0e000000000000000000000000000000000000000000000000000000008152600160a060020a038381166004830152909116906329092d0e90602401600060405180830381600087803b15801561048857600080fd5b505af115801561049c573d6000803e3d6000fd5b5050604051600160a060020a03841681527fa3b62bc36326052d97ea62d63c3d60308ed4c3ea8ac079dd8499f1e9c4f80c0f9250602001905060405180910390a150565b60008060009054906101000a9004600160a060020a0316600160a060020a031663949d225d6040518163ffffffff1660e060020a028152600401602060405180830381865afa158015610537573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061055b91906106dc565b905090565b600254600160a060020a031633146105e35760405160e560020a62461bcd02815260206004820152602e60248201527f5468652073656e646572206d757374206265207468652063726561746f72206f60448201527f662074686520636f6e74726163740000000000000000000000000000000000006064820152608401610217565b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600080546040517ff6a3d24e000000000000000000000000000000000000000000000000000000008152600160a060020a0384811660048301529091169063f6a3d24e90602401602060405180830381865afa158015610676573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061069a919061076f565b92915050565b600160a060020a03811681146106b557600080fd5b50565b6000602082840312156106ca57600080fd5b81356106d5816106a0565b9392505050565b6000602082840312156106ee57600080fd5b5051919050565b60006020828403121561070757600080fd5b81516106d5816106a0565b6020808252602e908201527f5468652073656e646572206d75737420626520746865204e6f6465436f6e747260408201527f6f6c6c657220636f6e7472616374000000000000000000000000000000000000606082015260800190565b60006020828403121561078157600080fd5b815180151581146106d557600080fdfea2646970667358221220effaee052c37a2dd33d0f2031fd5a10f91a64b7826e1ee77cf6d0b84fd479b8c64736f6c634300080c0033608060405234801561001057600080fd5b5060028054600160a060020a03191633179055610663806100326000396000f3fe608060405234801561001057600080fd5b506004361061007e577c010000000000000000000000000000000000000000000000000000000060003504630a3b0a4f811461008357806329092d0e146100985780632d883a73146100ab578063949d225d146100db578063c5eff3d0146100ec578063f6a3d24e14610101575b600080fd5b61009661009136600461049e565b61013c565b005b6100966100a636600461049e565b610213565b6100be6100b93660046104ce565b61038b565b604051600160a060020a0390911681526020015b60405180910390f35b6000546040519081526020016100d2565b6100f461043c565b6040516100d291906104e7565b61012c61010f36600461049e565b600160a060020a0316600090815260016020526040902054151590565b60405190151581526020016100d2565b600254600160a060020a03163314610189576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161018090610534565b60405180910390fd5b600160a060020a0381166000908152600160205260409020546102105760008054600180820183557f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563909101805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03851690811790915582549083526020919091526040909120555b50565b600254600160a060020a03163314610257576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161018090610534565b600160a060020a0381166000908152600160205260409020541561021057600160a060020a0381166000908152600160205260408120549054811461032e576000805481906102a890600190610591565b815481106102b8576102b86105cf565b6000918252602080832090910154600160a060020a03168083526001918290526040832085905592508291906102ee9085610591565b815481106102fe576102fe6105cf565b9060005260206000200160006101000a815481600160a060020a030219169083600160a060020a03160217905550505b600160a060020a0382166000908152600160205260408120819055805480610358576103586105fe565b6000828152602090208101600019908101805473ffffffffffffffffffffffffffffffffffffffff191690550190555050565b6000808211801561039e57506000548211155b610404576040517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152600d60248201527f4f7574206f6620626f756e6473000000000000000000000000000000000000006044820152606401610180565b6000610411600184610591565b81548110610421576104216105cf565b600091825260209091200154600160a060020a031692915050565b6060600080548060200260200160405190810160405280929190818152602001828054801561049457602002820191906000526020600020905b8154600160a060020a03168152600190910190602001808311610476575b5050505050905090565b6000602082840312156104b057600080fd5b8135600160a060020a03811681146104c757600080fd5b9392505050565b6000602082840312156104e057600080fd5b5035919050565b6020808252825182820181905260009190848201906040850190845b81811015610528578351600160a060020a031683529284019291840191600101610503565b50909695505050505050565b6020808252602c908201527f5468652073656e646572206d75737420626520746865206f776e6572206f662060408201527f74686520636f6e74726163740000000000000000000000000000000000000000606082015260800190565b6000828210156105ca577f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b500390565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea26469706673582212205baf05c5af170a4ef7a0fac3747a10e94ff712434f3b5ab5b18f70c16f054cd264736f6c634300080c0033";

    public static final String FUNC_ADD = "add";

    public static final String FUNC_GETADMINSTORAGE = "getAdminStorage";

    public static final String FUNC_ISAUTHORIZED = "isAuthorized";

    public static final String FUNC_REMOVE = "remove";

    public static final String FUNC_SETADMINSTORAGE = "setAdminStorage";

    public static final String FUNC_SIZE = "size";

    public static final Event ADMINADDED_EVENT = new Event("AdminAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event ADMINREMOVED_EVENT = new Event("AdminRemoved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Admin(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Admin(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Admin(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Admin(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AdminAddedEventResponse> getAdminAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADMINADDED_EVENT, transactionReceipt);
        ArrayList<AdminAddedEventResponse> responses = new ArrayList<AdminAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AdminAddedEventResponse typedResponse = new AdminAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.admin = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AdminAddedEventResponse> adminAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AdminAddedEventResponse>() {
            @Override
            public AdminAddedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADMINADDED_EVENT, log);
                AdminAddedEventResponse typedResponse = new AdminAddedEventResponse();
                typedResponse.log = log;
                typedResponse.admin = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AdminAddedEventResponse> adminAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADMINADDED_EVENT));
        return adminAddedEventFlowable(filter);
    }

    public List<AdminRemovedEventResponse> getAdminRemovedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADMINREMOVED_EVENT, transactionReceipt);
        ArrayList<AdminRemovedEventResponse> responses = new ArrayList<AdminRemovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AdminRemovedEventResponse typedResponse = new AdminRemovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.admin = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AdminRemovedEventResponse> adminRemovedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AdminRemovedEventResponse>() {
            @Override
            public AdminRemovedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADMINREMOVED_EVENT, log);
                AdminRemovedEventResponse typedResponse = new AdminRemovedEventResponse();
                typedResponse.log = log;
                typedResponse.admin = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AdminRemovedEventResponse> adminRemovedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADMINREMOVED_EVENT));
        return adminRemovedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> add(String admin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, admin)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getAdminStorage() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETADMINSTORAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isAuthorized(String source) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAUTHORIZED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, source)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> remove(String admin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, admin)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAdminStorage(String newStorage) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETADMINSTORAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newStorage)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> size() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Admin load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Admin(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Admin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Admin(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Admin load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Admin(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Admin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Admin(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Admin> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _registryContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _registryContract)));
        return deployRemoteCall(Admin.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Admin> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _registryContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _registryContract)));
        return deployRemoteCall(Admin.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Admin> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _registryContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _registryContract)));
        return deployRemoteCall(Admin.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Admin> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _registryContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _registryContract)));
        return deployRemoteCall(Admin.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class AdminAddedEventResponse extends BaseEventResponse {
        public String admin;
    }

    public static class AdminRemovedEventResponse extends BaseEventResponse {
        public String admin;
    }
}
