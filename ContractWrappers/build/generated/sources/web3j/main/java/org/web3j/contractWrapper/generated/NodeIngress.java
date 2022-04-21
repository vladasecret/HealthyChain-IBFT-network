package org.web3j.contractWrapper.generated;

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
    public static final String BINARY = "608060405234801561001057600080fd5b50610545806100206000396000f3fe608060405234801561001057600080fd5b506004361061005d577c01000000000000000000000000000000000000000000000000000000006000350463028ebc44811461006257806345a59e5b14610077578063574d51e21461009e575b600080fd5b610075610070366004610332565b6100b1565b005b61008a61008536600461039f565b610108565b604051901515815260200160405180910390f35b6100756100ac366004610438565b6102e2565b600154600160a060020a0316156100d957600154600160a060020a031633146100d957600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b60008054600160a060020a0316610121575060016102d9565b60008054604080517f6abffc8f0000000000000000000000000000000000000000000000000000000081529051600160a060020a0390921691630d2020dd918391636abffc8f916004808201926020929091908290030181865afa15801561018d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906101b19190610455565b6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016101eb91815260200190565b602060405180830381865afa158015610208573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061022c919061046e565b9050600160a060020a038116156102d3576040517f45a59e5b000000000000000000000000000000000000000000000000000000008152600160a060020a038216906345a59e5b9061028a908a908a908a908a908a906004016104b4565b602060405180830381865afa1580156102a7573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102cb91906104f2565b9150506102d9565b60019150505b95945050505050565b60405181151581527f66120f934b66d52127e448f8e94c2460ea62821335e0dd18e89ed38a4a09b4139060200160405180910390a150565b600160a060020a038116811461032f57600080fd5b50565b60006020828403121561034457600080fd5b813561034f8161031a565b9392505050565b60008083601f84011261036857600080fd5b50813567ffffffffffffffff81111561038057600080fd5b60208301915083602082850101111561039857600080fd5b9250929050565b6000806000806000606086880312156103b757600080fd5b853567ffffffffffffffff808211156103cf57600080fd5b6103db89838a01610356565b909750955060208801359150808211156103f457600080fd5b5061040188828901610356565b909450925050604086013561ffff8116811461041c57600080fd5b809150509295509295909350565b801515811461032f57600080fd5b60006020828403121561044a57600080fd5b813561034f8161042a565b60006020828403121561046757600080fd5b5051919050565b60006020828403121561048057600080fd5b815161034f8161031a565b81835281816020850137506000828201602090810191909152601f909101601f19169091010190565b6060815260006104c860608301878961048b565b82810360208401526104db81868861048b565b91505061ffff831660408301529695505050505050565b60006020828403121561050457600080fd5b815161034f8161042a56fea26469706673582212206dc24144df7609139ae9b1ded379f611998108a431164d00ac359e96b40dee3864736f6c634300080c0033";

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

    @Deprecated
    public static RemoteCall<NodeIngress> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NodeIngress.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<NodeIngress> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NodeIngress.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NodeIngress> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NodeIngress.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class NodePermissionsUpdatedEventResponse extends BaseEventResponse {
        public Boolean addsRestrictions;
    }
}
