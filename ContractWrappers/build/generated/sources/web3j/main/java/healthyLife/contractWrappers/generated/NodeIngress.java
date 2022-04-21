package healthyLife.contractWrappers.generated;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
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
public class NodeIngress extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060018054600160a060020a031916331790556105cc806100326000396000f3fe608060405234801561001057600080fd5b506004361061005d577c01000000000000000000000000000000000000000000000000000000006000350463028ebc44811461006257806345a59e5b14610077578063574d51e21461009e575b600080fd5b6100756100703660046103b9565b6100b1565b005b61008a610085366004610426565b61018f565b604051901515815260200160405180910390f35b6100756100ac3660046104bf565b610369565b600154600160a060020a03161561016057600154600160a060020a03163314610160576040517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152602a60248201527f5468652073656e646572206d7573742062652063726561746f72206f6620746860448201527f6520636f6e747261637400000000000000000000000000000000000000000000606482015260840160405180910390fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b60008054600160a060020a03166101a857506001610360565b60008054604080517f6abffc8f0000000000000000000000000000000000000000000000000000000081529051600160a060020a0390921691630d2020dd918391636abffc8f916004808201926020929091908290030181865afa158015610214573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061023891906104dc565b6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161027291815260200190565b602060405180830381865afa15801561028f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102b391906104f5565b9050600160a060020a0381161561035a576040517f45a59e5b000000000000000000000000000000000000000000000000000000008152600160a060020a038216906345a59e5b90610311908a908a908a908a908a9060040161053b565b602060405180830381865afa15801561032e573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103529190610579565b915050610360565b60019150505b95945050505050565b60405181151581527f66120f934b66d52127e448f8e94c2460ea62821335e0dd18e89ed38a4a09b4139060200160405180910390a150565b600160a060020a03811681146103b657600080fd5b50565b6000602082840312156103cb57600080fd5b81356103d6816103a1565b9392505050565b60008083601f8401126103ef57600080fd5b50813567ffffffffffffffff81111561040757600080fd5b60208301915083602082850101111561041f57600080fd5b9250929050565b60008060008060006060868803121561043e57600080fd5b853567ffffffffffffffff8082111561045657600080fd5b61046289838a016103dd565b9097509550602088013591508082111561047b57600080fd5b50610488888289016103dd565b909450925050604086013561ffff811681146104a357600080fd5b809150509295509295909350565b80151581146103b657600080fd5b6000602082840312156104d157600080fd5b81356103d6816104b1565b6000602082840312156104ee57600080fd5b5051919050565b60006020828403121561050757600080fd5b81516103d6816103a1565b81835281816020850137506000828201602090810191909152601f909101601f19169091010190565b60608152600061054f606083018789610512565b8281036020840152610562818688610512565b91505061ffff831660408301529695505050505050565b60006020828403121561058b57600080fd5b81516103d6816104b156fea26469706673582212207ff36bc1708289c64814367f5bfc36eb260de2c682aaa507ad456779d1f9f25864736f6c634300080c0033";

    public static final String FUNC_CONNECTIONALLOWED = "connectionAllowed";

    public static final String FUNC_EMITNODEPERMISSIONSEVENT = "emitNodePermissionsEvent";

    public static final String FUNC_SETREGISTRYCONTRACT = "setRegistryContract";

    public static final Event NODEPERMISSIONSUPDATED_EVENT = new Event("NodePermissionsUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected NodeIngress(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NodeIngress(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NodeIngress(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NodeIngress(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<NodePermissionsUpdatedEventResponse> getNodePermissionsUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NODEPERMISSIONSUPDATED_EVENT, transactionReceipt);
        ArrayList<NodePermissionsUpdatedEventResponse> responses = new ArrayList<NodePermissionsUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NodePermissionsUpdatedEventResponse typedResponse = new NodePermissionsUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addsRestrictions = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NodePermissionsUpdatedEventResponse> nodePermissionsUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NodePermissionsUpdatedEventResponse>() {
            @Override
            public NodePermissionsUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NODEPERMISSIONSUPDATED_EVENT, log);
                NodePermissionsUpdatedEventResponse typedResponse = new NodePermissionsUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.addsRestrictions = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NodePermissionsUpdatedEventResponse> nodePermissionsUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODEPERMISSIONSUPDATED_EVENT));
        return nodePermissionsUpdatedEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> connectionAllowed(String enodeId, String enodeHost, BigInteger enodePort) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CONNECTIONALLOWED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> emitNodePermissionsEvent(Boolean addsRestrictions) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_EMITNODEPERMISSIONSEVENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(addsRestrictions)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setRegistryContract(String _registryContractAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETREGISTRYCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _registryContractAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static NodeIngress load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NodeIngress(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NodeIngress load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NodeIngress(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NodeIngress load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NodeIngress(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NodeIngress load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NodeIngress(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NodeIngress> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NodeIngress.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<NodeIngress> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NodeIngress.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NodeIngress> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NodeIngress.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NodeIngress> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NodeIngress.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class NodePermissionsUpdatedEventResponse extends BaseEventResponse {
        public Boolean addsRestrictions;
    }
}
