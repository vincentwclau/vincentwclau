package com.muecode.insurance.smartcontract.travel.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple10;
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
 * <p>Generated with web3j version 4.9.4.
 */
@SuppressWarnings("rawtypes")
public class TravelInsurance extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162000bfd38038062000bfd83398101604081905262000034916200019a565b60008890556001620000478882620002fd565b506002620000568782620002fd565b506003620000658682620002fd565b50600480546001600160a01b039586166001600160a01b031991821617909155600580549490951693169290921790925560069190915560075550506008805461ffff1916600117905550620003c99050565b634e487b7160e01b600052604160045260246000fd5b600082601f830112620000e057600080fd5b81516001600160401b0380821115620000fd57620000fd620000b8565b604051601f8301601f19908116603f01168101908282118183101715620001285762000128620000b8565b816040528381526020925086838588010111156200014557600080fd5b600091505b838210156200016957858201830151818301840152908201906200014a565b600093810190920192909252949350505050565b80516001600160a01b03811681146200019557600080fd5b919050565b600080600080600080600080610100898b031215620001b857600080fd5b885160208a01519098506001600160401b0380821115620001d857600080fd5b620001e68c838d01620000ce565b985060408b0151915080821115620001fd57600080fd5b6200020b8c838d01620000ce565b975060608b01519150808211156200022257600080fd5b50620002318b828c01620000ce565b9550506200024260808a016200017d565b93506200025260a08a016200017d565b60c08a015160e0909a0151989b979a5095989497939692505050565b600181811c908216806200028357607f821691505b602082108103620002a457634e487b7160e01b600052602260045260246000fd5b50919050565b601f821115620002f857600081815260208120601f850160051c81016020861015620002d35750805b601f850160051c820191505b81811015620002f457828155600101620002df565b5050505b505050565b81516001600160401b03811115620003195762000319620000b8565b62000331816200032a84546200026e565b84620002aa565b602080601f831160018114620003695760008415620003505750858301515b600019600386901b1c1916600185901b178555620002f4565b600085815260208120601f198616915b828110156200039a5788860151825594840194600190910190840162000379565b5085821015620003b95787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b61082480620003d96000396000f3fe60806040526004361061003f5760003560e01c80633a0132c7146100445780633bc5de301461004e57806373d4a13a14610079578063a75ac608146100a4575b600080fd5b61004c6100ac565b005b34801561005a57600080fd5b5061006361010c565b6040516100709190610656565b60405180910390f35b34801561008557600080fd5b5061008e61039c565b6040516100709a9998979695949392919061072e565b61004c610587565b6004546001600160a01b031633146100c357600080fd5b6005546006546040516001600160a01b039092169181156108fc0291906000818181858888f193505050501580156100ff573d6000803e3d6000fd5b506008805460ff19169055565b6101786040518061014001604052806000815260200160608152602001606081526020016060815260200160006001600160a01b0316815260200160006001600160a01b0316815260200160008152602001600081526020016000151581526020016000151581525090565b6040805161014081019091526000805482526001805460208401919061019d906107b4565b80601f01602080910402602001604051908101604052809291908181526020018280546101c9906107b4565b80156102165780601f106101eb57610100808354040283529160200191610216565b820191906000526020600020905b8154815290600101906020018083116101f957829003601f168201915b5050505050815260200160028201805461022f906107b4565b80601f016020809104026020016040519081016040528092919081815260200182805461025b906107b4565b80156102a85780601f1061027d576101008083540402835291602001916102a8565b820191906000526020600020905b81548152906001019060200180831161028b57829003601f168201915b505050505081526020016003820180546102c1906107b4565b80601f01602080910402602001604051908101604052809291908181526020018280546102ed906107b4565b801561033a5780601f1061030f5761010080835404028352916020019161033a565b820191906000526020600020905b81548152906001019060200180831161031d57829003601f168201915b505050918352505060048201546001600160a01b0390811660208301526005830154166040820152600682015460608201526007820154608082015260089091015460ff808216151560a084015261010090910416151560c090910152919050565b60008054600180549192916103b0906107b4565b80601f01602080910402602001604051908101604052809291908181526020018280546103dc906107b4565b80156104295780601f106103fe57610100808354040283529160200191610429565b820191906000526020600020905b81548152906001019060200180831161040c57829003601f168201915b50505050509080600201805461043e906107b4565b80601f016020809104026020016040519081016040528092919081815260200182805461046a906107b4565b80156104b75780601f1061048c576101008083540402835291602001916104b7565b820191906000526020600020905b81548152906001019060200180831161049a57829003601f168201915b5050505050908060030180546104cc906107b4565b80601f01602080910402602001604051908101604052809291908181526020018280546104f8906107b4565b80156105455780601f1061051a57610100808354040283529160200191610545565b820191906000526020600020905b81548152906001019060200180831161052857829003601f168201915b505050600484015460058501546006860154600787015460089097015495966001600160a01b0393841696929093169450925060ff808216916101009004168a565b6004546001600160a01b0316331461059e57600080fd5b60085460ff166105ad57600080fd5b600854610100900460ff16156105c257600080fd5b6005546007546040516001600160a01b039092169181156108fc0291906000818181858888f193505050501580156105fe573d6000803e3d6000fd5b506008805461ff001916610100179055565b6000815180845260005b818110156106365760208185018101518683018201520161061a565b506000602082860101526020601f19601f83011685010191505092915050565b60208152815160208201526000602083015161014080604085015261067f610160850183610610565b91506040850151601f198086850301606087015261069d8483610610565b93506060870151915080868503016080870152506106bb8382610610565b92505060808501516106d860a08601826001600160a01b03169052565b5060a08501516001600160a01b03811660c08601525060c085015160e085015260e085015161010081818701528087015191505061012061071c8187018315159052565b90950151151593019290925250919050565b60006101408c83528060208401526107488184018d610610565b9050828103604084015261075c818c610610565b90508281036060840152610770818b610610565b6001600160a01b03998a1660808501529790981660a08301525060c081019490945260e0840192909252151561010083015215156101209091015295945050505050565b600181811c908216806107c857607f821691505b6020821081036107e857634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212200ada242d5dec36f0defdb9e9a2f5f602ea5d2bb0d8b56306c7f3702644af62df64736f6c63430008110033";

    public static final String FUNC_CANCELINSURANCE = "cancelInsurance";

    public static final String FUNC_CLAIMINSURANCE = "claimInsurance";

    public static final String FUNC_DATA = "data";

    public static final String FUNC_GETDATA = "getData";

    @Deprecated
    protected TravelInsurance(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TravelInsurance(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TravelInsurance(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TravelInsurance(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelInsurance(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CANCELINSURANCE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> claimInsurance(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CLAIMINSURANCE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<Tuple10<BigInteger, String, String, String, String, String, BigInteger, BigInteger, Boolean, Boolean>> data() {
        final Function function = new Function(FUNC_DATA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple10<BigInteger, String, String, String, String, String, BigInteger, BigInteger, Boolean, Boolean>>(function,
                new Callable<Tuple10<BigInteger, String, String, String, String, String, BigInteger, BigInteger, Boolean, Boolean>>() {
                    @Override
                    public Tuple10<BigInteger, String, String, String, String, String, BigInteger, BigInteger, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple10<BigInteger, String, String, String, String, String, BigInteger, BigInteger, Boolean, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (Boolean) results.get(8).getValue(), 
                                (Boolean) results.get(9).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TravelInsuranceData> getData() {
        final Function function = new Function(FUNC_GETDATA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<TravelInsuranceData>() {}));
        return executeRemoteCallSingleValueReturn(function, TravelInsuranceData.class);
    }

    @Deprecated
    public static TravelInsurance load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TravelInsurance(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TravelInsurance load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TravelInsurance(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TravelInsurance load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TravelInsurance(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TravelInsurance load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TravelInsurance(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TravelInsurance> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _templateId, String _templateName, String _flightNumber, String _departureTime, String _insurer, String _insured, BigInteger _premium, BigInteger _payoutAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_templateId), 
                new org.web3j.abi.datatypes.Utf8String(_templateName), 
                new org.web3j.abi.datatypes.Utf8String(_flightNumber), 
                new org.web3j.abi.datatypes.Utf8String(_departureTime), 
                new org.web3j.abi.datatypes.Address(160, _insurer), 
                new org.web3j.abi.datatypes.Address(160, _insured), 
                new org.web3j.abi.datatypes.generated.Uint256(_premium), 
                new org.web3j.abi.datatypes.generated.Uint256(_payoutAmount)));
        return deployRemoteCall(TravelInsurance.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TravelInsurance> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _templateId, String _templateName, String _flightNumber, String _departureTime, String _insurer, String _insured, BigInteger _premium, BigInteger _payoutAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_templateId), 
                new org.web3j.abi.datatypes.Utf8String(_templateName), 
                new org.web3j.abi.datatypes.Utf8String(_flightNumber), 
                new org.web3j.abi.datatypes.Utf8String(_departureTime), 
                new org.web3j.abi.datatypes.Address(160, _insurer), 
                new org.web3j.abi.datatypes.Address(160, _insured), 
                new org.web3j.abi.datatypes.generated.Uint256(_premium), 
                new org.web3j.abi.datatypes.generated.Uint256(_payoutAmount)));
        return deployRemoteCall(TravelInsurance.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TravelInsurance> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _templateId, String _templateName, String _flightNumber, String _departureTime, String _insurer, String _insured, BigInteger _premium, BigInteger _payoutAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_templateId), 
                new org.web3j.abi.datatypes.Utf8String(_templateName), 
                new org.web3j.abi.datatypes.Utf8String(_flightNumber), 
                new org.web3j.abi.datatypes.Utf8String(_departureTime), 
                new org.web3j.abi.datatypes.Address(160, _insurer), 
                new org.web3j.abi.datatypes.Address(160, _insured), 
                new org.web3j.abi.datatypes.generated.Uint256(_premium), 
                new org.web3j.abi.datatypes.generated.Uint256(_payoutAmount)));
        return deployRemoteCall(TravelInsurance.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TravelInsurance> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _templateId, String _templateName, String _flightNumber, String _departureTime, String _insurer, String _insured, BigInteger _premium, BigInteger _payoutAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_templateId), 
                new org.web3j.abi.datatypes.Utf8String(_templateName), 
                new org.web3j.abi.datatypes.Utf8String(_flightNumber), 
                new org.web3j.abi.datatypes.Utf8String(_departureTime), 
                new org.web3j.abi.datatypes.Address(160, _insurer), 
                new org.web3j.abi.datatypes.Address(160, _insured), 
                new org.web3j.abi.datatypes.generated.Uint256(_premium), 
                new org.web3j.abi.datatypes.generated.Uint256(_payoutAmount)));
        return deployRemoteCall(TravelInsurance.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class TravelInsuranceData extends DynamicStruct {
        public BigInteger templateId;

        public String templateName;

        public String flightNumber;

        public String departureTime;

        public String insurer;

        public String insured;

        public BigInteger premium;

        public BigInteger payoutAmount;

        public Boolean isActive;

        public Boolean isPaidOut;

        public TravelInsuranceData(BigInteger templateId, String templateName, String flightNumber, String departureTime, String insurer, String insured, BigInteger premium, BigInteger payoutAmount, Boolean isActive, Boolean isPaidOut) {
            super(new org.web3j.abi.datatypes.generated.Uint256(templateId), 
                    new org.web3j.abi.datatypes.Utf8String(templateName), 
                    new org.web3j.abi.datatypes.Utf8String(flightNumber), 
                    new org.web3j.abi.datatypes.Utf8String(departureTime), 
                    new org.web3j.abi.datatypes.Address(160, insurer), 
                    new org.web3j.abi.datatypes.Address(160, insured), 
                    new org.web3j.abi.datatypes.generated.Uint256(premium), 
                    new org.web3j.abi.datatypes.generated.Uint256(payoutAmount), 
                    new org.web3j.abi.datatypes.Bool(isActive), 
                    new org.web3j.abi.datatypes.Bool(isPaidOut));
            this.templateId = templateId;
            this.templateName = templateName;
            this.flightNumber = flightNumber;
            this.departureTime = departureTime;
            this.insurer = insurer;
            this.insured = insured;
            this.premium = premium;
            this.payoutAmount = payoutAmount;
            this.isActive = isActive;
            this.isPaidOut = isPaidOut;
        }

        public TravelInsuranceData(Uint256 templateId, Utf8String templateName, Utf8String flightNumber, Utf8String departureTime, Address insurer, Address insured, Uint256 premium, Uint256 payoutAmount, Bool isActive, Bool isPaidOut) {
            super(templateId, templateName, flightNumber, departureTime, insurer, insured, premium, payoutAmount, isActive, isPaidOut);
            this.templateId = templateId.getValue();
            this.templateName = templateName.getValue();
            this.flightNumber = flightNumber.getValue();
            this.departureTime = departureTime.getValue();
            this.insurer = insurer.getValue();
            this.insured = insured.getValue();
            this.premium = premium.getValue();
            this.payoutAmount = payoutAmount.getValue();
            this.isActive = isActive.getValue();
            this.isPaidOut = isPaidOut.getValue();
        }
    }
}
