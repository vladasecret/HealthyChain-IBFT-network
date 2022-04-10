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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class HelloWorld extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161083b38038061083b83398101604081905261002f91610123565b60008054600160a060020a03191633179055805161005490600190602084019061005b565b5050610247565b828054610067906101f3565b90600052602060002090601f01602090048101928261008957600085556100cf565b82601f106100a257805160ff19168380011785556100cf565b828001600101855582156100cf579182015b828111156100cf5782518255916020019190600101906100b4565b506100db9291506100df565b5090565b5b808211156100db57600081556001016100e0565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6000602080838503121561013657600080fd5b825167ffffffffffffffff8082111561014e57600080fd5b818501915085601f83011261016257600080fd5b815181811115610174576101746100f4565b604051601f8201601f19908116603f0116810190838211818310171561019c5761019c6100f4565b8160405282815288868487010111156101b457600080fd5b600093505b828410156101d657848401860151818501870152928501926101b9565b828411156101e75760008684830101525b98975050505050505050565b60028104600182168061020757607f821691505b60208210811415610241577f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b50919050565b6105e5806102566000396000f3fe608060405234801561001057600080fd5b5060043610610052577c010000000000000000000000000000000000000000000000000000000060003504634ac0d66e8114610057578063ef690cc01461006c575b600080fd5b61006a61006536600461030d565b61008a565b005b6100746101b3565b604051610081919061041a565b60405180910390f35b60005473ffffffffffffffffffffffffffffffffffffffff163314610135576040517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152602260248201527f4f6e6c79206f776e65722063616e2063616c6c20746869732066756e6374696f60448201527f6e2e000000000000000000000000000000000000000000000000000000000000606482015260840160405180910390fd5b806040516101439190610434565b6040518091039020600160405161015a91906104a4565b60405180910390207f047dcd1aa8b77b0b943642129c767533eeacd700c7c1eab092b8ce05d2b2faf5600184604051610194929190610516565b60405180910390a380516101af906001906020840190610245565b5050565b6060600180546101c290610450565b80601f01602080910402602001604051908101604052809291908181526020018280546101ee90610450565b801561023b5780601f106102105761010080835404028352916020019161023b565b820191906000526020600020905b81548152906001019060200180831161021e57829003601f168201915b5050505050905090565b82805461025190610450565b90600052602060002090601f01602090048101928261027357600085556102b9565b82601f1061028c57805160ff19168380011785556102b9565b828001600101855582156102b9579182015b828111156102b957825182559160200191906001019061029e565b506102c59291506102c9565b5090565b5b808211156102c557600081556001016102ca565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b60006020828403121561031f57600080fd5b813567ffffffffffffffff8082111561033757600080fd5b818401915084601f83011261034b57600080fd5b81358181111561035d5761035d6102de565b604051601f8201601f19908116603f01168101908382118183101715610385576103856102de565b8160405282815287602084870101111561039e57600080fd5b826020860160208301376000928101602001929092525095945050505050565b60005b838110156103d95781810151838201526020016103c1565b838111156103e8576000848401525b50505050565b600081518084526104068160208601602086016103be565b601f01601f19169290920160200192915050565b60208152600061042d60208301846103ee565b9392505050565b600082516104468184602087016103be565b9190910192915050565b60028104600182168061046457607f821691505b6020821081141561049e577f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b50919050565b60008083546104b281610450565b600182811680156104ca57600181146104db5761050a565b60ff1984168752828701945061050a565b8760005260208060002060005b858110156105015781548a8201529084019082016104e8565b50505082870194505b50929695505050505050565b60408152600080845461052881610450565b806040860152606060018084166000811461054a576001811461055e5761058f565b60ff1985168884015260808801955061058f565b8960005260208060002060005b868110156105865781548b820187015290840190820161056b565b8a018501975050505b505050505082810360208401526105a681856103ee565b9594505050505056fea26469706673582212204237d5225e49a8d23699b836c7c296a3a6393af4809a272c5acb8f72f353caef64736f6c634300080c0033";

    public static final String FUNC_GREETING = "greeting";

    public static final String FUNC_NEWGREETING = "newGreeting";

    public static final Event MODIFIED_EVENT = new Event("Modified", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected HelloWorld(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected HelloWorld(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected HelloWorld(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected HelloWorld(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ModifiedEventResponse> getModifiedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MODIFIED_EVENT, transactionReceipt);
        ArrayList<ModifiedEventResponse> responses = new ArrayList<ModifiedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ModifiedEventResponse typedResponse = new ModifiedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldGreetingIdx = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newGreetingIdx = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.oldGreeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newGreeting = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ModifiedEventResponse> modifiedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ModifiedEventResponse>() {
            @Override
            public ModifiedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MODIFIED_EVENT, log);
                ModifiedEventResponse typedResponse = new ModifiedEventResponse();
                typedResponse.log = log;
                typedResponse.oldGreetingIdx = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newGreetingIdx = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.oldGreeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.newGreeting = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ModifiedEventResponse> modifiedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MODIFIED_EVENT));
        return modifiedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> greeting() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GREETING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> newGreeting(String _greet) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_NEWGREETING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static HelloWorld load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorld(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static HelloWorld load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorld(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static HelloWorld load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new HelloWorld(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static HelloWorld load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new HelloWorld(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<HelloWorld> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _greet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)));
        return deployRemoteCall(HelloWorld.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<HelloWorld> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _greet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)));
        return deployRemoteCall(HelloWorld.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<HelloWorld> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _greet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)));
        return deployRemoteCall(HelloWorld.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<HelloWorld> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _greet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)));
        return deployRemoteCall(HelloWorld.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ModifiedEventResponse extends BaseEventResponse {
        public byte[] oldGreetingIdx;

        public byte[] newGreetingIdx;

        public String oldGreeting;

        public String newGreeting;
    }
}
