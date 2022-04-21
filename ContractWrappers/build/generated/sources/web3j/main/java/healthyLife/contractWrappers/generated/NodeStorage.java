package healthyLife.contractWrappers.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
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
public class NodeStorage extends Contract {
    public static final String BINARY = "60806040526003805460ff1916600117905534801561001d57600080fd5b5060008054600160a060020a0319163317905561166d8061003f6000396000f3fe608060405234801561001057600080fd5b5060043610610107576000357c01000000000000000000000000000000000000000000000000000000009004806377dc3aec116100a95780639dfefe5d116100835780639dfefe5d14610200578063b8debb1b14610213578063da85216c14610226578063f49b92231461023957600080fd5b806377dc3aec146101d85780637e04cd98146101e5578063949d225d146101f857600080fd5b80634f558e79116100e55780634f558e791461016f5780635a5e4a761461019157806369c45824146101b25780637598bc2f146101c557600080fd5b806313af40351461010c5780632d883a731461012157806347c065cd1461014c575b600080fd5b61011f61011a366004611107565b61024c565b005b61013461012f36600461113d565b6102cb565b604051610143939291906111b2565b60405180910390f35b61015f61015a3660046112a4565b6104b6565b6040519015158152602001610143565b61015f61017d36600461113d565b600090815260026020526040902054151590565b6101a461019f366004611318565b6104e4565b604051908152602001610143565b6101346101c036600461113d565b61050f565b61015f6101d336600461138a565b61065d565b60035461015f9060ff1681565b6101a46101f33660046113ac565b610a8d565b6001546101a4565b6101a461020e3660046112a4565b610ae6565b6101a46102213660046112a4565b610b0c565b61015f6102343660046112a4565b610b7f565b61015f6102473660046112a4565b610ed1565b60005473ffffffffffffffffffffffffffffffffffffffff16331461028f5760405160e560020a62461bcd02815260040161028690611464565b60405180910390fd5b6000805473ffffffffffffffffffffffffffffffffffffffff191673ffffffffffffffffffffffffffffffffffffffff92909216919091179055565b6060806000600184101580156102e357506001548411155b6103325760405160e560020a62461bcd02815260206004820152600d60248201527f4f7574206f6620626f756e6473000000000000000000000000000000000000006044820152606401610286565b6000600161034081876114da565b81548110610350576103506114f1565b90600052602060002090600302016040518060600160405290816000820180546103799061150a565b80601f01602080910402602001604051908101604052809291908181526020018280546103a59061150a565b80156103f25780601f106103c7576101008083540402835291602001916103f2565b820191906000526020600020905b8154815290600101906020018083116103d557829003601f168201915b5050505050815260200160018201805461040b9061150a565b80601f01602080910402602001604051908101604052809291908181526020018280546104379061150a565b80156104845780601f1061045957610100808354040283529160200191610484565b820191906000526020600020905b81548152906001019060200180831161046757829003601f168201915b50505091835250506002919091015461ffff166020918201528151908201516040909201519097919650945092505050565b6000600260006104c7868686610b0c565b8152602001908152602001600020546000141590505b9392505050565b6000600483836040516104f8929190611548565b908152602001604051809103902054905092915050565b6001818154811061051f57600080fd5b90600052602060002090600302016000915090508060000180546105429061150a565b80601f016020809104026020016040519081016040528092919081815260200182805461056e9061150a565b80156105bb5780601f10610590576101008083540402835291602001916105bb565b820191906000526020600020905b81548152906001019060200180831161059e57829003601f168201915b5050505050908060010180546105d09061150a565b80601f01602080910402602001604051908101604052809291908181526020018280546105fc9061150a565b80156106495780601f1061061e57610100808354040283529160200191610649565b820191906000526020600020905b81548152906001019060200180831161062c57829003601f168201915b5050506002909301549192505061ffff1683565b6000805473ffffffffffffffffffffffffffffffffffffffff1633146106985760405160e560020a62461bcd02815260040161028690611464565b60035460ff16151582151514156106b157506000919050565b60005b60015481101561087a576000600182815481106106d3576106d36114f1565b90600052602060002090600302016040518060600160405290816000820180546106fc9061150a565b80601f01602080910402602001604051908101604052809291908181526020018280546107289061150a565b80156107755780601f1061074a57610100808354040283529160200191610775565b820191906000526020600020905b81548152906001019060200180831161075857829003601f168201915b5050505050815260200160018201805461078e9061150a565b80601f01602080910402602001604051908101604052809291908181526020018280546107ba9061150a565b80156108075780601f106107dc57610100808354040283529160200191610807565b820191906000526020600020905b8154815290600101906020018083116107ea57829003601f168201915b505050918352505060029182015461ffff166020909101529091506000908161082f84610a8d565b8152602001908152602001600020819055506000600482600001516040516108579190611558565b90815260405190819003602001902055508061087281611574565b9150506106b4565b506003805460ff191683151517905560005b600154811015610a82576000600182815481106108ab576108ab6114f1565b90600052602060002090600302016040518060600160405290816000820180546108d49061150a565b80601f01602080910402602001604051908101604052809291908181526020018280546109009061150a565b801561094d5780601f106109225761010080835404028352916020019161094d565b820191906000526020600020905b81548152906001019060200180831161093057829003601f168201915b505050505081526020016001820180546109669061150a565b80601f01602080910402602001604051908101604052809291908181526020018280546109929061150a565b80156109df5780601f106109b4576101008083540402835291602001916109df565b820191906000526020600020905b8154815290600101906020018083116109c257829003601f168201915b50505091835250506002919091015461ffff1660209091015290506000610a0582610a8d565b600081815260026020526040902054909150610a5457600160048360000151604051610a319190611558565b90815260200160405180910390206000828254610a4e919061158f565b90915550505b610a5f83600161158f565b600091825260026020526040909120555080610a7a81611574565b91505061088c565b50600190505b919050565b60035460009060ff1615610acb578151604051610aad9190602001611558565b60408051601f19818403018152919052805160209091012092915050565b81516020808401516040808601519051610aad9493016115a7565b600080610af4858585610b0c565b60009081526002602052604090205495945050505050565b60035460009060ff1615610b4a5783604051602001610b2b9190611558565b60408051601f19818403018152919052805160209091012090506104dd565b838383604051602001610b5f939291906115a7565b60408051808303601f190181529190528051602090910120949350505050565b6000805473ffffffffffffffffffffffffffffffffffffffff163314610bba5760405160e560020a62461bcd02815260040161028690611464565b6000610bc7858585610b0c565b60008181526002602052604090205460015491925090811115610bec57610bec611605565b6001811015610c00576000925050506104dd565b6001548114610e32576001805460009190610c1c9082906114da565b81548110610c2c57610c2c6114f1565b9060005260206000209060030201604051806060016040529081600082018054610c559061150a565b80601f0160208091040260200160405190810160405280929190818152602001828054610c819061150a565b8015610cce5780601f10610ca357610100808354040283529160200191610cce565b820191906000526020600020905b815481529060010190602001808311610cb157829003601f168201915b50505050508152602001600182018054610ce79061150a565b80601f0160208091040260200160405190810160405280929190818152602001828054610d139061150a565b8015610d605780601f10610d3557610100808354040283529160200191610d60565b820191906000526020600020905b815481529060010190602001808311610d4357829003601f168201915b50505091835250506002919091015461ffff166020909101529050806001610d8881856114da565b81548110610d9857610d986114f1565b90600052602060002090600302016000820151816000019080519060200190610dc2929190611031565b506020828101518051610ddb9260018501920190611031565b50604091909101516002909101805461ffff191661ffff9092169190911790556000610e0682610a8d565b60008181526002602052604090205490915015610e2f5760008181526002602052604090208390555b50505b6000828152600260205260408120556001805480610e5257610e5261161e565b60008281526020812060001990920191600383020190610e7282826110b5565b610e806001830160006110b5565b50600201805461ffff191690559055604051600190600490610ea3908990611558565b90815260200160405180910390206000828254610ec091906114da565b909155506001979650505050505050565b6000805473ffffffffffffffffffffffffffffffffffffffff163314610f0c5760405160e560020a62461bcd02815260040161028690611464565b6000610f19858585610b0c565b60008181526002602052604090205490915015610f3a5760009150506104dd565b60408051606081018252868152602080820187905261ffff86169282019290925260018054808201825560009190915281518051929360039092027fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf60192610fa59284920190611031565b506020828101518051610fbe9260018501920190611031565b506040918201516002918201805461ffff191661ffff90921691909117905560018054600085815260209390935291839020919091559051600490611004908890611558565b90815260200160405180910390206000828254611021919061158f565b9091555060019695505050505050565b82805461103d9061150a565b90600052602060002090601f01602090048101928261105f57600085556110a5565b82601f1061107857805160ff19168380011785556110a5565b828001600101855582156110a5579182015b828111156110a557825182559160200191906001019061108a565b506110b19291506110f2565b5090565b5080546110c19061150a565b6000825580601f106110d1575050565b601f0160209004906000526020600020908101906110ef91906110f2565b50565b5b808211156110b157600081556001016110f3565b60006020828403121561111957600080fd5b813573ffffffffffffffffffffffffffffffffffffffff811681146104dd57600080fd5b60006020828403121561114f57600080fd5b5035919050565b60005b83811015611171578181015183820152602001611159565b83811115611180576000848401525b50505050565b6000815180845261119e816020860160208601611156565b601f01601f19169290920160200192915050565b6060815260006111c56060830186611186565b82810360208401526111d78186611186565b91505061ffff83166040830152949350505050565b60e060020a634e487b7102600052604160045260246000fd5b600082601f83011261121657600080fd5b813567ffffffffffffffff80821115611231576112316111ec565b604051601f8301601f19908116603f01168101908282118183101715611259576112596111ec565b8160405283815286602085880101111561127257600080fd5b836020870160208301376000602085830101528094505050505092915050565b803561ffff81168114610a8857600080fd5b6000806000606084860312156112b957600080fd5b833567ffffffffffffffff808211156112d157600080fd5b6112dd87838801611205565b945060208601359150808211156112f357600080fd5b5061130086828701611205565b92505061130f60408501611292565b90509250925092565b6000806020838503121561132b57600080fd5b823567ffffffffffffffff8082111561134357600080fd5b818501915085601f83011261135757600080fd5b81358181111561136657600080fd5b86602082850101111561137857600080fd5b60209290920196919550909350505050565b60006020828403121561139c57600080fd5b813580151581146104dd57600080fd5b6000602082840312156113be57600080fd5b813567ffffffffffffffff808211156113d657600080fd5b90830190606082860312156113ea57600080fd5b604051606081018181108382111715611405576114056111ec565b60405282358281111561141757600080fd5b61142387828601611205565b82525060208301358281111561143857600080fd5b61144487828601611205565b60208301525061145660408401611292565b604082015295945050505050565b6020808252602c908201527f5468652073656e646572206d75737420626520746865206f776e6572206f662060408201527f74686520636f6e74726163740000000000000000000000000000000000000000606082015260800190565b60e060020a634e487b7102600052601160045260246000fd5b6000828210156114ec576114ec6114c1565b500390565b60e060020a634e487b7102600052603260045260246000fd5b60028104600182168061151e57607f821691505b602082108114156115425760e060020a634e487b7102600052602260045260246000fd5b50919050565b8183823760009101908152919050565b6000825161156a818460208701611156565b9190910192915050565b6000600019821415611588576115886114c1565b5060010190565b600082198211156115a2576115a26114c1565b500190565b600084516115b9818460208901611156565b8451908301906115cd818360208901611156565b61ffff949094167e01000000000000000000000000000000000000000000000000000000000000029301928352505060020192915050565b60e060020a634e487b7102600052600160045260246000fd5b60e060020a634e487b7102600052603160045260246000fdfea2646970667358221220964e450094cc873adce5e9e740f07587790f3a7147793694d2bb4a0dcd3767a164736f6c634300080c0033";

    public static final String FUNC_IDONLYMODE = "IdOnlyMode";

    public static final String FUNC_ADD = "add";

    public static final String FUNC_ALLOWLIST = "allowlist";

    public static final String FUNC_calculateKey = "calculateKey";

    public static final String FUNC_exists = "exists";

    public static final String FUNC_GETBYINDEX = "getByIndex";

    public static final String FUNC_GETENODEIDNUM = "getEnodeIdNum";

    public static final String FUNC_GETINDEXOF = "getIndexOf";

    public static final String FUNC_REMOVE = "remove";

    public static final String FUNC_SETIDONLYMODE = "setIdOnlyMode";

    public static final String FUNC_SETOWNER = "setOwner";

    public static final String FUNC_SIZE = "size";

    @Deprecated
    protected NodeStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NodeStorage(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NodeStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NodeStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Boolean> IdOnlyMode() {
        final Function function = new Function(FUNC_IDONLYMODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> add(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<String, String, BigInteger>> allowlist(BigInteger param0) {
        final Function function = new Function(FUNC_ALLOWLIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint16>() {}));
        return new RemoteFunctionCall<Tuple3<String, String, BigInteger>>(function,
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> calculateKey(Enode enode) {
        final Function function = new Function(FUNC_calculateKey, 
                Arrays.<Type>asList(enode), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> calculateKey(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(FUNC_calculateKey, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> exists(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(FUNC_exists, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> exists(BigInteger key) {
        final Function function = new Function(FUNC_exists, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple3<String, String, BigInteger>> getByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETBYINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint16>() {}));
        return new RemoteFunctionCall<Tuple3<String, String, BigInteger>>(function,
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getEnodeIdNum(String enodeId) {
        final Function function = new Function(FUNC_GETENODEIDNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getIndexOf(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(FUNC_GETINDEXOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> remove(String enodeId, String enodeHost, BigInteger enodePort) {
        final Function function = new Function(
                FUNC_REMOVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                new org.web3j.abi.datatypes.generated.Uint16(enodePort)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setIdOnlyMode(Boolean value) {
        final Function function = new Function(
                FUNC_SETIDONLYMODE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOwner(String _address) {
        final Function function = new Function(
                FUNC_SETOWNER, 
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
    public static NodeStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NodeStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NodeStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NodeStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NodeStorage load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NodeStorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NodeStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NodeStorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NodeStorage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NodeStorage.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<NodeStorage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NodeStorage.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NodeStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NodeStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NodeStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NodeStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Enode extends DynamicStruct {
        public String enodeId;

        public String enodeHost;

        public BigInteger enodePort;

        public Enode(String enodeId, String enodeHost, BigInteger enodePort) {
            super(new org.web3j.abi.datatypes.Utf8String(enodeId), 
                    new org.web3j.abi.datatypes.Utf8String(enodeHost), 
                    new org.web3j.abi.datatypes.generated.Uint16(enodePort));
            this.enodeId = enodeId;
            this.enodeHost = enodeHost;
            this.enodePort = enodePort;
        }

        public Enode(Utf8String enodeId, Utf8String enodeHost, Uint16 enodePort) {
            super(enodeId, enodeHost, enodePort);
            this.enodeId = enodeId.getValue();
            this.enodeHost = enodeHost.getValue();
            this.enodePort = enodePort.getValue();
        }
    }
}
