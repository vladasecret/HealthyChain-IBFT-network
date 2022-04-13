package healthyLife.contractWrappers.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint8;
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
public class AccountController extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161183b38038061183b83398101604081905261002f916100d9565b60028054600160a060020a031916600160a060020a038416179055604051610056906100b4565b604051809103906000f080158015610072573d6000803e3d6000fd5b5060008054600160a060020a03928316600160a060020a0319918216179091556001805493909216928116929092179055600380549091163317905550610113565b6107c58061107683390190565b600160a060020a03811681146100d657600080fd5b50565b600080604083850312156100ec57600080fd5b82516100f7816100c1565b6020840151909250610108816100c1565b809150509250929050565b610f54806101226000396000f3fe608060405234801561001057600080fd5b50600436106100b0576000357c010000000000000000000000000000000000000000000000000000000090048063936421d511610083578063936421d514610119578063a6e497b61461013c578063b583127d14610167578063c3c5a5471461017a578063c54f16921461018d57600080fd5b8063090fc537146100b55780630ad2cd20146100ca57806312886441146100dd5780631f735227146100f0575b600080fd5b6100c86100c3366004610c2f565b6101a0565b005b6100c86100d8366004610c2f565b610205565b6100c86100eb366004610c2f565b610325565b6101036100fe366004610c2f565b61054e565b6040516101109190610cbd565b60405180910390f35b61012c610127366004610ccb565b6105dc565b6040519015158152602001610110565b61014f61014a366004610c2f565b6106fe565b604051600160a060020a039091168152602001610110565b6100c8610175366004610c2f565b610805565b61012c610188366004610c2f565b610861565b6100c861019b366004610c2f565b610991565b600354600160a060020a031633146101d65760405160e560020a62461bcd0281526004016101cd90610d80565b60405180910390fd5b6001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b60025460405160e260020a630935e01b028152336004820152600160a060020a03909116906324d7806c90602401602060405180830381865afa158015610250573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102749190610ddd565b6102c35760405160e560020a62461bcd02815260206004820152601860248201527f5468652073656e646572206d7573742062652061646d696e000000000000000060448201526064016101cd565b600160a060020a0381163b156102ee5760405160e560020a62461bcd0281526004016101cd90610dff565b6102f781610861565b156103175760405160e560020a62461bcd0281526004016101cd90610e5c565b6103228160016109ec565b50565b60025460405160e260020a630935e01b028152336004820152600160a060020a03909116906324d7806c90602401602060405180830381865afa158015610370573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103949190610ddd565b6103e35760405160e560020a62461bcd02815260206004820152601860248201527f5468652073656e646572206d7573742062652061646d696e000000000000000060448201526064016101cd565b600160a060020a0381163b1561040e5760405160e560020a62461bcd0281526004016101cd90610dff565b61041781610861565b156104375760405160e560020a62461bcd0281526004016101cd90610e5c565b60025460405160e260020a630935e01b028152600160a060020a038381166004830152909116906324d7806c90602401602060405180830381865afa158015610484573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104a89190610ddd565b6105435760405160e560020a62461bcd02815260206004820152604160248201527f546f20726567697374657220616e206164647265737320617320612070726f7660448201527f696465722c206974206d75737420626520616e2061646d696e6973747261746f60648201527f7200000000000000000000000000000000000000000000000000000000000000608482015260a4016101cd565b6103228160026109ec565b600080546040517f1f735227000000000000000000000000000000000000000000000000000000008152600160a060020a03848116600483015290911690631f73522790602401602060405180830381865afa1580156105b2573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105d69190610e93565b92915050565b6000841515806105eb57508515155b156105f8575060006106f3565b600160a060020a0388163b6106ef57600354600160a060020a0389811691161480610627575061062788610861565b8061069e575060025460405160e260020a630935e01b028152600160a060020a038a81166004830152909116906324d7806c90602401602060405180830381865afa15801561067a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061069e9190610ddd565b6106aa575060006106f3565b600160a060020a0387161580156106cf5750600354600160a060020a03898116911614155b806106e25750600160a060020a0387163b155b156106ef575060006106f3565b5060015b979650505050505050565b600061070982610861565b61077e5760405160e560020a62461bcd02815260206004820152602f60248201527f556e61626c6520746f206765742055736572436f6e74726163743a207573657260448201527f206e6f742072656769737465726564000000000000000000000000000000000060648201526084016101cd565b6000546040517fa6e497b6000000000000000000000000000000000000000000000000000000008152600160a060020a0384811660048301529091169063a6e497b690602401602060405180830381865afa1580156107e1573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105d69190610eb4565b600354600160a060020a031633146108325760405160e560020a62461bcd0281526004016101cd90610d80565b6000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600080546040517ff6a3d24e000000000000000000000000000000000000000000000000000000008152600160a060020a0384811660048301529091169063f6a3d24e90602401602060405180830381865afa1580156108c5573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108e99190610ddd565b80156105d657506000546040517f1f735227000000000000000000000000000000000000000000000000000000008152600160a060020a0384811660048301526003921690631f73522790602401602060405180830381865afa158015610954573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109789190610e93565b600381111561098957610989610c53565b141592915050565b600160a060020a0381163b156109bc5760405160e560020a62461bcd0281526004016101cd90610dff565b6109c581610861565b156109e55760405160e560020a62461bcd0281526004016101cd90610e5c565b6103228160005b6001546040517f6697a437000000000000000000000000000000000000000000000000000000008152600091600160a060020a031690636697a43790610a389086908590600401610ed1565b6020604051808303816000875af1158015610a57573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610a7b9190610eb4565b905082600160a060020a031681600160a060020a031663893d20e86040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381865afa158015610ae1573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610b059190610eb4565b600160a060020a031614610b845760405160e560020a62461bcd02815260206004820152602960248201527f55736572436f6e7472616374206372656174656420776974682077726f6e672060448201527f6f776e657273686970000000000000000000000000000000000000000000000060648201526084016101cd565b6000546040517f287a074e000000000000000000000000000000000000000000000000000000008152600160a060020a039091169063287a074e90610bd190869086908690600401610eee565b6020604051808303816000875af1158015610bf0573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610c149190610ddd565b50505050565b600160a060020a038116811461032257600080fd5b600060208284031215610c4157600080fd5b8135610c4c81610c1a565b9392505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b60048110610cb9577f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b9052565b602081016105d68284610c82565b600080600080600080600060c0888a031215610ce657600080fd5b8735610cf181610c1a565b96506020880135610d0181610c1a565b955060408801359450606088013593506080880135925060a088013567ffffffffffffffff80821115610d3357600080fd5b818a0191508a601f830112610d4757600080fd5b813581811115610d5657600080fd5b8b6020828501011115610d6857600080fd5b60208301945080935050505092959891949750929550565b6020808252602e908201527f5468652073656e646572206d757374206265207468652063726561746f72206f60408201527f662074686520636f6e7472616374000000000000000000000000000000000000606082015260800190565b600060208284031215610def57600080fd5b81518015158114610c4c57600080fd5b6020808252602a908201527f52656769737465726564207573657220616464726573732063616e6e6f74206860408201527f617665206120636f646500000000000000000000000000000000000000000000606082015260800190565b6020808252601f908201527f54686973207573657220697320616c7265616479207265676973746572656400604082015260600190565b600060208284031215610ea557600080fd5b815160048110610c4c57600080fd5b600060208284031215610ec657600080fd5b8151610c4c81610c1a565b600160a060020a038316815260408101610c4c6020830184610c82565b600160a060020a0384811682526060820190610f0d6020840186610c82565b80841660408401525094935050505056fea26469706673582212200f8dbcf3abc47067513bad6928c77f740a2550cda1531b156c51a008b8a87d7764736f6c634300080c0033608060405234801561001057600080fd5b5060028054600160a060020a03191633179055610793806100326000396000f3fe608060405234801561001057600080fd5b506004361061009a576000357c0100000000000000000000000000000000000000000000000000000000900480637b510fe8116100785780637b510fe814610100578063949d225d14610122578063a6e497b614610133578063f6a3d24e1461015e57600080fd5b80631f7352271461009f578063287a074e146100c857806329092d0e146100eb575b600080fd5b6100b26100ad3660046105d9565b610189565b6040516100bf9190610632565b60405180910390f35b6100db6100d6366004610646565b61025e565b60405190151581526020016100bf565b6100fe6100f93660046105d9565b6103e9565b005b61011361010e3660046105d9565b6104dc565b6040516100bf9392919061068f565b6000546040519081526020016100bf565b6101466101413660046105d9565b610564565b604051600160a060020a0390911681526020016100bf565b6100db61016c3660046105d9565b600160a060020a0316600090815260016020526040902054151590565b600160a060020a0381166000908152600160205260408120548061020e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601660248201527f4163636f756e74206e6f7420726567697374657265640000000000000000000060448201526064015b60405180910390fd5b600061021b6001836106bf565b8154811061022b5761022b6106e7565b600091825260209091206002909102015474010000000000000000000000000000000000000000900460ff169392505050565b600254600090600160a060020a031633146102a5576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161020590610700565b600160a060020a0384166000908152600160205260409020546103de576000604051806060016040528086600160a060020a031681526020018560038111156102f0576102f06105f4565b8152600160a060020a038086166020928301528354600181018555600094855293829020835160029095020180549490911673ffffffffffffffffffffffffffffffffffffffff19851681178255918301519293909291839174ffffffffffffffffffffffffffffffffffffffffff1916177401000000000000000000000000000000000000000083600381111561038a5761038a6105f4565b02179055506040918201516001918201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039283161790556000805491881681526020839052929092209190915590506103e2565b5060005b9392505050565b600254600160a060020a0316331461042d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161020590610700565b600160a060020a038116600090815260016020526040902054801580159061045757506000548111155b156104d8576000805460039190610470906001906106bf565b81548110610480576104806106e7565b60009182526020909120600290910201805474ff00000000000000000000000000000000000000001916740100000000000000000000000000000000000000008360038111156104d2576104d26105f4565b02179055505b5050565b600160a060020a0381166000908152600160208190526040822054829182918291829161050991906106bf565b81548110610519576105196106e7565b600091825260209091206002909102018054600190910154600160a060020a03808316987401000000000000000000000000000000000000000090930460ff16975016945092505050565b600160a060020a0381166000908152600160208190526040822054829161058a916106bf565b8154811061059a5761059a6106e7565b6000918252602090912060016002909202010154600160a060020a031692915050565b8035600160a060020a03811681146105d457600080fd5b919050565b6000602082840312156105eb57600080fd5b6103e2826105bd565b60e060020a634e487b7102600052602160045260246000fd5b6004811061062e5760e060020a634e487b7102600052602160045260246000fd5b9052565b60208101610640828461060d565b92915050565b60008060006060848603121561065b57600080fd5b610664846105bd565b925060208401356004811061067857600080fd5b9150610686604085016105bd565b90509250925092565b600160a060020a03848116825260608201906106ae602084018661060d565b808416604084015250949350505050565b6000828210156106e25760e060020a634e487b7102600052601160045260246000fd5b500390565b60e060020a634e487b7102600052603260045260246000fd5b6020808252602c908201527f5468652073656e646572206d75737420626520746865206f776e6572206f662060408201527f74686520636f6e7472616374000000000000000000000000000000000000000060608201526080019056fea2646970667358221220bd3313ea42ec7bf00a0841929e7ac51ae731fa43751642b1fdbd3bc692ba373664736f6c634300080c0033";

    public static final String FUNC_GETUSERCLASS = "getUserClass";

    public static final String FUNC_GETUSERCONTRACTADDRESS = "getUserContractAddress";

    public static final String FUNC_ISREGISTERED = "isRegistered";

    public static final String FUNC_REGISTRYDOCTOR = "registryDoctor";

    public static final String FUNC_REGISTRYPATIENT = "registryPatient";

    public static final String FUNC_REGISTRYPROVIDER = "registryProvider";

    public static final String FUNC_SETACCOUNTSTORAGE = "setAccountStorage";

    public static final String FUNC_SETUSERCONTRACTSFACTORY = "setUserContractsFactory";

    public static final String FUNC_TRANSACTIONALLOWED = "transactionAllowed";

    @Deprecated
    protected AccountController(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AccountController(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AccountController(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AccountController(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> getUserClass(String _address) {
        final Function function = new Function(FUNC_GETUSERCLASS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getUserContractAddress(String account) {
        final Function function = new Function(FUNC_GETUSERCONTRACTADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isRegistered(String account) {
        final Function function = new Function(FUNC_ISREGISTERED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> registryDoctor(String account) {
        final Function function = new Function(
                FUNC_REGISTRYDOCTOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registryPatient(String account) {
        final Function function = new Function(
                FUNC_REGISTRYPATIENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registryProvider(String account) {
        final Function function = new Function(
                FUNC_REGISTRYPROVIDER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAccountStorage(String _accountStorage) {
        final Function function = new Function(
                FUNC_SETACCOUNTSTORAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _accountStorage)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setUserContractsFactory(String _userContractFactory) {
        final Function function = new Function(
                FUNC_SETUSERCONTRACTSFACTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _userContractFactory)), 
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
    public static AccountController load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccountController(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AccountController load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccountController(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AccountController load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AccountController(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AccountController load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AccountController(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AccountController> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _registryContract, String _userContractFactory) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _registryContract), 
                new org.web3j.abi.datatypes.Address(160, _userContractFactory)));
        return deployRemoteCall(AccountController.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<AccountController> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _registryContract, String _userContractFactory) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _registryContract), 
                new org.web3j.abi.datatypes.Address(160, _userContractFactory)));
        return deployRemoteCall(AccountController.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AccountController> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _registryContract, String _userContractFactory) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _registryContract), 
                new org.web3j.abi.datatypes.Address(160, _userContractFactory)));
        return deployRemoteCall(AccountController.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AccountController> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _registryContract, String _userContractFactory) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _registryContract), 
                new org.web3j.abi.datatypes.Address(160, _userContractFactory)));
        return deployRemoteCall(AccountController.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}