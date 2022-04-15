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
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
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
public class PermissionsContract extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162001b9c38038062001b9c833981016040819052620000349162000544565b6000805433600160a060020a03199182161790915560018054909116600160a060020a03841617905562000074826002836401000000006200007c810204565b50506200068d565b600160a060020a0383166000908152600360205260409020541562000102576040517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601c60248201527f5573657220616c726561647920686173207065726d697373696f6e730000000060448201526064015b60405180910390fd5b620001163364010000000062000333810204565b8062000132575062000132328364010000000062000347810204565b620001c0576040517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152603860248201527f5468652073656e646572206f6620746865207472616e73616374696f6e20636160448201527f6e6e6f74206164642074686973207065726d697373696f6e00000000000000006064820152608401620000f9565b6000620001d63364010000000062000333810204565b8015620001f857506002836002811115620001f557620001f5620005f0565b14155b15620002115750600154600160a060020a031662000214565b50325b6002604051806080016040528086600160a060020a03168152602001856002811115620002455762000245620005f0565b81526020808201869052600160a060020a0380861660409093019290925283546001810185556000948552938190208351600490950201805494909216600160a060020a03198516811783559083015192939192918391600160a860020a03199091161774010000000000000000000000000000000000000000836002811115620002d457620002d4620005f0565b02179055506040828101518051600184015560209081015160028085019190915560609094015160039384018054600160a060020a031916600160a060020a039283161790559354979093166000908152919092522093909355505050565b600054600160a060020a0391821691161490565b60006002826002811115620003605762000360620005f0565b141562000370575060006200043e565b620003848364010000000062000444810204565b156200043a57600160a060020a03831660009081526003602052604090205460016002620003b46001846200061f565b81548110620003c757620003c76200065e565b600091825260209091206004909102015474010000000000000000000000000000000000000000900460ff166002811115620004075762000407620005f0565b14156200042f576000836002811115620004255762000425620005f0565b149150506200043e565b60019150506200043e565b5060005b92915050565b600160a060020a038116600090815260036020526040812054806200046c5750600092915050565b6002806200047c6001846200061f565b815481106200048f576200048f6200065e565b600091825260209091206004909102015474010000000000000000000000000000000000000000900460ff166002811115620004cf57620004cf620005f0565b14806200053d575060016002620004e86001846200061f565b81548110620004fb57620004fb6200065e565b600091825260209091206004909102015474010000000000000000000000000000000000000000900460ff1660028111156200053b576200053b620005f0565b145b9392505050565b60008082840360608112156200055957600080fd5b8351600160a060020a03811681146200057157600080fd5b92506040601f19820112156200058657600080fd5b506040516040810181811067ffffffffffffffff82111715620005d2577f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b60409081526020858101518352940151938101939093525092909150565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b60008282101562000659577f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b500390565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b6114ff806200069d6000396000f3fe608060405234801561001057600080fd5b50600436106100c6576000357c010000000000000000000000000000000000000000000000000000000090048063949d225d1161008e578063949d225d14610173578063a607d88e14610184578063b919dfff14610199578063c3a07df6146101ac578063e074bb47146101c1578063f6a3d24e146101d457600080fd5b80631709ef07146100cb57806327951291146100f357806349d055841461011e578063795d08261461013e5780638f28ca431461015e575b600080fd5b6100de6100d93660046111b8565b6101ff565b60405190151581526020015b60405180910390f35b60015461010690600160a060020a031681565b604051600160a060020a0390911681526020016100ea565b61013161012c3660046111eb565b61025b565b6040516100ea9190611206565b61015161014c3660046111eb565b61034b565b6040516100ea91906112aa565b61017161016c3660046112e0565b6104bb565b005b6002546040519081526020016100ea565b61018c61072e565b6040516100ea9190611373565b6101716101a73660046113d4565b61085a565b6101b4610ac8565b6040516100ea91906113fe565b6101716101cf3660046111eb565b610c4a565b6100de6101e23660046111eb565b600160a060020a0316600090815260036020526040902054151590565b600154600090600160a060020a038085169116148061022f575081600160a060020a031683600160a060020a0316145b80156102525750600160a060020a03831660009081526003602052604090205415155b90505b92915050565b604080518082019091526000808252602082015261027932836101ff565b80610288575061028832611004565b6102dc5760405160e560020a62461bcd02815260206004820152601d60248201527f5573657220646f6573206e6f742068617665207065726d697373696f6e00000060448201526064015b60405180910390fd5b600160a060020a03821660009081526003602052604090205460029061030490600190611465565b815481106103145761031461147c565b9060005260206000209060040201600101604051806040016040529081600082015481526020016001820154815250509050919050565b60408051608081018252600080825260208083018290528351808501855282815290810182905292820192909252606081019190915261038b32836101ff565b8061039a575061039a32611004565b6103e95760405160e560020a62461bcd02815260206004820152601d60248201527f5573657220646f6573206e6f742068617665207065726d697373696f6e00000060448201526064016102d3565b600160a060020a03821660009081526003602052604090205460029061041190600190611465565b815481106104215761042161147c565b600091825260209182902060408051608081019091526004909202018054600160a060020a0381168352919290919083019060ff60a060020a90910416600281111561046f5761046f61121d565b60028111156104805761048061121d565b8152604080518082018252600184015481526002840154602082810191909152830152600390920154600160a060020a031691015292915050565b600160a060020a038316600090815260036020526040902054156105245760405160e560020a62461bcd02815260206004820152601c60248201527f5573657220616c726561647920686173207065726d697373696f6e730000000060448201526064016102d3565b600054600160a060020a0316331480610542575061054232836110d0565b6105b75760405160e560020a62461bcd02815260206004820152603860248201527f5468652073656e646572206f6620746865207472616e73616374696f6e20636160448201527f6e6e6f74206164642074686973207065726d697373696f6e000000000000000060648201526084016102d3565b60008054600160a060020a0316331480156105e4575060028360028111156105e1576105e161121d565b14155b156105fb5750600154600160a060020a03166105fe565b50325b6002604051806080016040528086600160a060020a0316815260200185600281111561062c5761062c61121d565b81526020808201869052600160a060020a038086166040909301929092528354600181018555600094855293819020835160049095020180549490921673ffffffffffffffffffffffffffffffffffffffff19851681178355908301519293919291839174ffffffffffffffffffffffffffffffffffffffffff199091161760a060020a8360028111156106c2576106c261121d565b0217905550604082810151805160018401556020908101516002808501919091556060909401516003938401805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039283161790559354979093166000908152919092522093909355505050565b60025460609060009067ffffffffffffffff81111561074f5761074f6112c7565b60405190808252806020026020018201604052801561079457816020015b604080518082019091526000808252602082015281526020019060019003908161076d5790505b50905060005b8151811015610854576040518060400160405280600283815481106107c1576107c161147c565b600091825260209182902060049091020154600160a060020a03168252600280549290910191849081106107f7576107f761147c565b600091825260209091206004909102015460a060020a900460ff1660028111156108235761082361121d565b8152508282815181106108385761083861147c565b60200260200101819052508061084d90611495565b905061079a565b50919050565b32600090815260036020526040902054151580156108d2575060023260009081526003602052604090205460029061089490600190611465565b815481106108a4576108a461147c565b600091825260209091206004909102015460a060020a900460ff1660028111156108d0576108d061121d565b145b6109475760405160e560020a62461bcd02815260206004820152603b60248201527f5468652073656e646572206f6620746865207472616e73616374696f6e206d7560448201527f737420626520746865206f776e6572206f66207468652064617461000000000060648201526084016102d3565b600160a060020a0382166000908152600360205260409020546109d55760405160e560020a62461bcd02815260206004820152602260248201527f546865207573657220646f6573206e6f742068617665207065726d697373696f60448201527f6e7300000000000000000000000000000000000000000000000000000000000060648201526084016102d3565b600160a060020a03821660009081526003602052604090205481906002906109ff90600190611465565b81548110610a0f57610a0f61147c565b60009182526020909120600490910201805474ff0000000000000000000000000000000000000000191660a060020a836002811115610a5057610a5061121d565b0217905550600160a060020a0382166000908152600360205260409020543290600290610a7f90600190611465565b81548110610a8f57610a8f61147c565b906000526020600020906004020160030160006101000a815481600160a060020a030219169083600160a060020a031602179055505050565b6060610ad332611004565b610b6e5760405160e560020a62461bcd02815260206004820152604a60248201527f546f2067657420696e666f726d6174696f6e2061626f7574207065726d69737360448201527f696f6e732c207468652073656e646572206d757374206861766520616363657360648201527f732063616e536861726500000000000000000000000000000000000000000000608482015260a4016102d3565b6002805480602002602001604051908101604052809291908181526020016000905b82821015610c415760008481526020908190206040805160808101909152600485029091018054600160a060020a0381168352919290919083019060a060020a900460ff166002811115610be657610be661121d565b6002811115610bf757610bf761121d565b81526040805180820182526001848101548252600285015460208381019190915280850192909252600390940154600160a060020a03169190920152918352929092019101610b90565b50505050905090565b600160a060020a038116600090815260036020526040902054610cd85760405160e560020a62461bcd02815260206004820152602160248201527f4e6f207065726d697373696f6e7320666f72207370656369666965642075736560448201527f720000000000000000000000000000000000000000000000000000000000000060648201526084016102d3565b600154600160a060020a0380831691161415610d395760405160e560020a62461bcd02815260206004820152601d60248201527f556e61626c6520746f2072656d6f7665206f776e65722773206461746100000060448201526064016102d3565b600154600160a060020a0316321480610da65750600160a060020a0381166000908152600360205260409020543290600290610d7790600190611465565b81548110610d8757610d8761147c565b6000918252602090912060036004909202010154600160a060020a0316145b610e1b5760405160e560020a62461bcd02815260206004820152603b60248201527f5468652073656e646572206f6620746865207472616e73616374696f6e20636160448201527f6e6e6f74206d616e6167652074686973207065726d697373696f6e000000000060648201526084016102d3565b600160a060020a0381166000908152600360205260409020546002548114610f77576002805460009190610e5190600190611465565b81548110610e6157610e6161147c565b6000918252602080832060049092029091018054600160a060020a03168352600390915260409091208390559050806002610e9d600185611465565b81548110610ead57610ead61147c565b600091825260209091208254600490920201805473ffffffffffffffffffffffffffffffffffffffff198116600160a060020a0390931692831782558354919260ff60a060020a938490041692849274ffffffffffffffffffffffffffffffffffffffffff191690911790836002811115610f2a57610f2a61121d565b021790555060018281015490820155600280830154908201556003918201549101805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909216919091179055505b6002805480610f8857610f886114b0565b60008281526020808220600460001990940193840201805474ffffffffffffffffffffffffffffffffffffffffff1916815560018101839055600281018390556003908101805473ffffffffffffffffffffffffffffffffffffffff1916905592909355600160a060020a039490941684529052506040812055565b600160a060020a0381166000908152600360205260408120548061102b5750600092915050565b600280611039600184611465565b815481106110495761104961147c565b600091825260209091206004909102015460a060020a900460ff1660028111156110755761107561121d565b14806110c957506001600261108b600184611465565b8154811061109b5761109b61147c565b600091825260209091206004909102015460a060020a900460ff1660028111156110c7576110c761121d565b145b9392505050565b600060028260028111156110e6576110e661121d565b14156110f457506000610255565b6110fd83611004565b1561119357600160a060020a0383166000908152600360205260409020546001600261112a600184611465565b8154811061113a5761113a61147c565b600091825260209091206004909102015460a060020a900460ff1660028111156111665761116661121d565b14156111895760008360028111156111805761118061121d565b14915050610255565b6001915050610255565b50600092915050565b8035600160a060020a03811681146111b357600080fd5b919050565b600080604083850312156111cb57600080fd5b6111d48361119c565b91506111e26020840161119c565b90509250929050565b6000602082840312156111fd57600080fd5b6102528261119c565b815181526020808301519082015260408101610255565b60e060020a634e487b7102600052602160045260246000fd5b600381106112575760e060020a634e487b7102600052602160045260246000fd5b9052565b600160a060020a03808251168352602082015161127b6020850182611236565b506040820151611298604085018280518252602090810151910152565b50806060830151166080840152505050565b60a08101610255828461125b565b8035600381106111b357600080fd5b60e060020a634e487b7102600052604160045260246000fd5b600080600083850360808112156112f657600080fd5b6112ff8561119c565b935061130d602086016112b8565b92506040603f198201121561132157600080fd5b506040516040810181811067ffffffffffffffff821117156113565760e060020a634e487b7102600052604160045260246000fd5b604090815285013581526060909401356020850152509093909250565b602080825282518282018190526000919060409081850190868401855b828110156113c75781518051600160a060020a031685528601516113b687860182611236565b509284019290850190600101611390565b5091979650505050505050565b600080604083850312156113e757600080fd5b6113f08361119c565b91506111e2602084016112b8565b6020808252825182820181905260009190848201906040850190845b818110156114405761142d83855161125b565b9284019260a0929092019160010161141a565b50909695505050505050565b60e060020a634e487b7102600052601160045260246000fd5b6000828210156114775761147761144c565b500390565b60e060020a634e487b7102600052603260045260246000fd5b60006000198214156114a9576114a961144c565b5060010190565b60e060020a634e487b7102600052603160045260246000fdfea26469706673582212201e024ec8277add91ee34c905cd6d7727afdb18ded8fe369094fd9a0d14b27b3f64736f6c634300080c0033";

    public static final String FUNC_ADDPERMISSION = "addPermission";

    public static final String FUNC_DATAOWNER = "dataOwner";

    public static final String FUNC_EDITPERMISSION = "editPermission";

    public static final String FUNC_EXISTS = "exists";

    public static final String FUNC_GETPERMISSIONINFO = "getPermissionInfo";

    public static final String FUNC_GETPERMISSIONS = "getPermissions";

    public static final String FUNC_GETPERMITTEDUSERS = "getPermittedUsers";

    public static final String FUNC_GETRECORDMETADATA = "getRecordMetadata";

    public static final String FUNC_HASACCESS = "hasAccess";

    public static final String FUNC_REMOVEPERMISSION = "removePermission";

    public static final String FUNC_SIZE = "size";

    public static final Event ADDEDPERMISSION_EVENT = new Event("addedPermission", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event REMOVEDPERMISSION_EVENT = new Event("removedPermission", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected PermissionsContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PermissionsContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PermissionsContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PermissionsContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AddedPermissionEventResponse> getAddedPermissionEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDEDPERMISSION_EVENT, transactionReceipt);
        ArrayList<AddedPermissionEventResponse> responses = new ArrayList<AddedPermissionEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddedPermissionEventResponse typedResponse = new AddedPermissionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.permissionLevel = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.permitter = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddedPermissionEventResponse> addedPermissionEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AddedPermissionEventResponse>() {
            @Override
            public AddedPermissionEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDEDPERMISSION_EVENT, log);
                AddedPermissionEventResponse typedResponse = new AddedPermissionEventResponse();
                typedResponse.log = log;
                typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.permissionLevel = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.permitter = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddedPermissionEventResponse> addedPermissionEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDEDPERMISSION_EVENT));
        return addedPermissionEventFlowable(filter);
    }

    public List<RemovedPermissionEventResponse> getRemovedPermissionEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REMOVEDPERMISSION_EVENT, transactionReceipt);
        ArrayList<RemovedPermissionEventResponse> responses = new ArrayList<RemovedPermissionEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RemovedPermissionEventResponse typedResponse = new RemovedPermissionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.permissionLevel = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.permitter = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RemovedPermissionEventResponse> removedPermissionEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RemovedPermissionEventResponse>() {
            @Override
            public RemovedPermissionEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REMOVEDPERMISSION_EVENT, log);
                RemovedPermissionEventResponse typedResponse = new RemovedPermissionEventResponse();
                typedResponse.log = log;
                typedResponse.user = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.permissionLevel = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.permitter = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RemovedPermissionEventResponse> removedPermissionEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REMOVEDPERMISSION_EVENT));
        return removedPermissionEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addPermission(String user, BigInteger level, RecordMetadata metadata) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user), 
                new org.web3j.abi.datatypes.generated.Uint8(level), 
                metadata), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> dataOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DATAOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> editPermission(String user, BigInteger newLevel) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_EDITPERMISSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user), 
                new org.web3j.abi.datatypes.generated.Uint8(newLevel)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> exists(String _address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_EXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<PermissionInfo> getPermissionInfo(String user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPERMISSIONINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<PermissionInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, PermissionInfo.class);
    }

    public RemoteFunctionCall<List> getPermissions() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPERMISSIONS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<PermissionInfo>>() {}));
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

    public RemoteFunctionCall<List> getPermittedUsers() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPERMITTEDUSERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<UserPermission>>() {}));
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

    public RemoteFunctionCall<RecordMetadata> getRecordMetadata(String user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRECORDMETADATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<RecordMetadata>() {}));
        return executeRemoteCallSingleValueReturn(function, RecordMetadata.class);
    }

    public RemoteFunctionCall<Boolean> hasAccess(String sender, String metadataOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HASACCESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, metadataOwner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removePermission(String user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEPERMISSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
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
    public static PermissionsContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PermissionsContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PermissionsContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PermissionsContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PermissionsContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PermissionsContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PermissionsContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PermissionsContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PermissionsContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String owner, RecordMetadata ownerMetadata) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                ownerMetadata));
        return deployRemoteCall(PermissionsContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PermissionsContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String owner, RecordMetadata ownerMetadata) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                ownerMetadata));
        return deployRemoteCall(PermissionsContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PermissionsContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String owner, RecordMetadata ownerMetadata) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                ownerMetadata));
        return deployRemoteCall(PermissionsContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PermissionsContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String owner, RecordMetadata ownerMetadata) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                ownerMetadata));
        return deployRemoteCall(PermissionsContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class RecordMetadata extends StaticStruct {
        public byte[] smk;

        public byte[] encodedHash;

        public RecordMetadata(byte[] smk, byte[] encodedHash) {
            super(new org.web3j.abi.datatypes.generated.Bytes32(smk), 
                    new org.web3j.abi.datatypes.generated.Bytes32(encodedHash));
            this.smk = smk;
            this.encodedHash = encodedHash;
        }

        public RecordMetadata(Bytes32 smk, Bytes32 encodedHash) {
            super(smk, encodedHash);
            this.smk = smk.getValue();
            this.encodedHash = encodedHash.getValue();
        }
    }

    public static class UserPermission extends StaticStruct {
        public String user;

        public BigInteger level;

        public UserPermission(String user, BigInteger level) {
            super(new org.web3j.abi.datatypes.Address(160, user), 
                    new org.web3j.abi.datatypes.generated.Uint8(level));
            this.user = user;
            this.level = level;
        }

        public UserPermission(Address user, Uint8 level) {
            super(user, level);
            this.user = user.getValue();
            this.level = level.getValue();
        }
    }

    public static class PermissionInfo extends StaticStruct {
        public String user;

        public BigInteger level;

        public RecordMetadata metadata;

        public String permitter;

        public PermissionInfo(String user, BigInteger level, RecordMetadata metadata, String permitter) {
            super(new org.web3j.abi.datatypes.Address(160, user), 
                    new org.web3j.abi.datatypes.generated.Uint8(level), 
                    metadata, 
                    new org.web3j.abi.datatypes.Address(160, permitter));
            this.user = user;
            this.level = level;
            this.metadata = metadata;
            this.permitter = permitter;
        }

        public PermissionInfo(Address user, Uint8 level, RecordMetadata metadata, Address permitter) {
            super(user, level, metadata, permitter);
            this.user = user.getValue();
            this.level = level.getValue();
            this.metadata = metadata;
            this.permitter = permitter.getValue();
        }
    }

    public static class AddedPermissionEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger permissionLevel;

        public String permitter;
    }

    public static class RemovedPermissionEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger permissionLevel;

        public String permitter;
    }
}
