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
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200170338038062001703833981810160405281019062000037919062000397565b8760008001819055508660006001019081620000549190620006fe565b508560006002019081620000699190620006fe565b5084600060030190816200007e9190620006fe565b5083600060040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600060050160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600060060181905550806000600701819055506001600060080160006101000a81548160ff02191690831515021790555060008060080160016101000a81548160ff0219169083151502179055505050505050505050620007e5565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b6200018d8162000178565b81146200019957600080fd5b50565b600081519050620001ad8162000182565b92915050565b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6200020882620001bd565b810181811067ffffffffffffffff821117156200022a5762000229620001ce565b5b80604052505050565b60006200023f62000164565b90506200024d8282620001fd565b919050565b600067ffffffffffffffff82111562000270576200026f620001ce565b5b6200027b82620001bd565b9050602081019050919050565b60005b83811015620002a85780820151818401526020810190506200028b565b60008484015250505050565b6000620002cb620002c58462000252565b62000233565b905082815260208101848484011115620002ea57620002e9620001b8565b5b620002f784828562000288565b509392505050565b600082601f830112620003175762000316620001b3565b5b815162000329848260208601620002b4565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006200035f8262000332565b9050919050565b620003718162000352565b81146200037d57600080fd5b50565b600081519050620003918162000366565b92915050565b600080600080600080600080610100898b031215620003bb57620003ba6200016e565b5b6000620003cb8b828c016200019c565b985050602089015167ffffffffffffffff811115620003ef57620003ee62000173565b5b620003fd8b828c01620002ff565b975050604089015167ffffffffffffffff81111562000421576200042062000173565b5b6200042f8b828c01620002ff565b965050606089015167ffffffffffffffff81111562000453576200045262000173565b5b620004618b828c01620002ff565b9550506080620004748b828c0162000380565b94505060a0620004878b828c0162000380565b93505060c06200049a8b828c016200019c565b92505060e0620004ad8b828c016200019c565b9150509295985092959890939650565b600081519050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806200051057607f821691505b602082108103620005265762000525620004c8565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b600060088302620005907fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8262000551565b6200059c868362000551565b95508019841693508086168417925050509392505050565b6000819050919050565b6000620005df620005d9620005d38462000178565b620005b4565b62000178565b9050919050565b6000819050919050565b620005fb83620005be565b620006136200060a82620005e6565b8484546200055e565b825550505050565b600090565b6200062a6200061b565b62000637818484620005f0565b505050565b5b818110156200065f576200065360008262000620565b6001810190506200063d565b5050565b601f821115620006ae5762000678816200052c565b620006838462000541565b8101602085101562000693578190505b620006ab620006a28562000541565b8301826200063c565b50505b505050565b600082821c905092915050565b6000620006d360001984600802620006b3565b1980831691505092915050565b6000620006ee8383620006c0565b9150826002028217905092915050565b6200070982620004bd565b67ffffffffffffffff811115620007255762000724620001ce565b5b620007318254620004f7565b6200073e82828562000663565b600060209050601f83116001811462000776576000841562000761578287015190505b6200076d8582620006e0565b865550620007dd565b601f19841662000786866200052c565b60005b82811015620007b05784890151825560018201915060208501945060208101905062000789565b86831015620007d05784890151620007cc601f891682620006c0565b8355505b6001600288020188555050505b505050505050565b610f0e80620007f56000396000f3fe60806040526004361061003f5760003560e01c80633a0132c7146100445780633bc5de301461004e57806373d4a13a14610079578063a75ac608146100ad575b600080fd5b61004c6100b7565b005b34801561005a57600080fd5b506100636101a4565b6040516100709190610a8f565b60405180910390f35b34801561008557600080fd5b5061008e610475565b6040516100a49a99989796959493929190610b28565b60405180910390f35b6100b56106a9565b005b600060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461011457600080fd5b600060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6000600601549081150290604051600060405180830381858888f19350505050158015610184573d6000803e3d6000fd5b5060008060080160006101000a81548160ff021916908315150217905550565b6101ac61081f565b600060405180610140016040529081600082015481526020016001820180546101d490610c08565b80601f016020809104026020016040519081016040528092919081815260200182805461020090610c08565b801561024d5780601f106102225761010080835404028352916020019161024d565b820191906000526020600020905b81548152906001019060200180831161023057829003601f168201915b5050505050815260200160028201805461026690610c08565b80601f016020809104026020016040519081016040528092919081815260200182805461029290610c08565b80156102df5780601f106102b4576101008083540402835291602001916102df565b820191906000526020600020905b8154815290600101906020018083116102c257829003601f168201915b505050505081526020016003820180546102f890610c08565b80601f016020809104026020016040519081016040528092919081815260200182805461032490610c08565b80156103715780601f1061034657610100808354040283529160200191610371565b820191906000526020600020905b81548152906001019060200180831161035457829003601f168201915b505050505081526020016004820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016005820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200160068201548152602001600782015481526020016008820160009054906101000a900460ff161515151581526020016008820160019054906101000a900460ff161515151581525050905090565b600080600001549080600101805461048c90610c08565b80601f01602080910402602001604051908101604052809291908181526020018280546104b890610c08565b80156105055780601f106104da57610100808354040283529160200191610505565b820191906000526020600020905b8154815290600101906020018083116104e857829003601f168201915b50505050509080600201805461051a90610c08565b80601f016020809104026020016040519081016040528092919081815260200182805461054690610c08565b80156105935780601f1061056857610100808354040283529160200191610593565b820191906000526020600020905b81548152906001019060200180831161057657829003601f168201915b5050505050908060030180546105a890610c08565b80601f01602080910402602001604051908101604052809291908181526020018280546105d490610c08565b80156106215780601f106105f657610100808354040283529160200191610621565b820191906000526020600020905b81548152906001019060200180831161060457829003601f168201915b5050505050908060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060060154908060070154908060080160009054906101000a900460ff16908060080160019054906101000a900460ff1690508a565b600060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461070657600080fd5b600060080160009054906101000a900460ff1661072257600080fd5b600060080160019054906101000a900460ff161561073f57600080fd5b600060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6000600701549081150290604051600060405180830381858888f193505050501580156107af573d6000803e3d6000fd5b506001600060080160016101000a81548160ff0219169083151502179055503373ffffffffffffffffffffffffffffffffffffffff167f3e2fd14d58008bf39b7103c57c9624a791d3f3f50b4483830d28dd2afa1cfc8560006040516108159190610eb6565b60405180910390a2565b60405180610140016040528060008152602001606081526020016060815260200160608152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff16815260200160008152602001600081526020016000151581526020016000151581525090565b6000819050919050565b6108b5816108a2565b82525050565b600081519050919050565b600082825260208201905092915050565b60005b838110156108f55780820151818401526020810190506108da565b60008484015250505050565b6000601f19601f8301169050919050565b600061091d826108bb565b61092781856108c6565b93506109378185602086016108d7565b61094081610901565b840191505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006109768261094b565b9050919050565b6109868161096b565b82525050565b60008115159050919050565b6109a18161098c565b82525050565b6000610140830160008301516109c060008601826108ac565b50602083015184820360208601526109d88282610912565b915050604083015184820360408601526109f28282610912565b91505060608301518482036060860152610a0c8282610912565b9150506080830151610a21608086018261097d565b5060a0830151610a3460a086018261097d565b5060c0830151610a4760c08601826108ac565b5060e0830151610a5a60e08601826108ac565b50610100830151610a6f610100860182610998565b50610120830151610a84610120860182610998565b508091505092915050565b60006020820190508181036000830152610aa981846109a7565b905092915050565b610aba816108a2565b82525050565b600082825260208201905092915050565b6000610adc826108bb565b610ae68185610ac0565b9350610af68185602086016108d7565b610aff81610901565b840191505092915050565b610b138161096b565b82525050565b610b228161098c565b82525050565b600061014082019050610b3e600083018d610ab1565b8181036020830152610b50818c610ad1565b90508181036040830152610b64818b610ad1565b90508181036060830152610b78818a610ad1565b9050610b876080830189610b0a565b610b9460a0830188610b0a565b610ba160c0830187610ab1565b610bae60e0830186610ab1565b610bbc610100830185610b19565b610bca610120830184610b19565b9b9a5050505050505050505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680610c2057607f821691505b602082108103610c3357610c32610bd9565b5b50919050565b60008160001c9050919050565b6000819050919050565b6000610c63610c5e83610c39565b610c46565b9050919050565b60008190508160005260206000209050919050565b60008154610c8c81610c08565b610c9681866108c6565b94506001821660008114610cb15760018114610cc757610cfa565b60ff198316865281151560200286019350610cfa565b610cd085610c6a565b60005b83811015610cf257815481890152600182019150602081019050610cd3565b808801955050505b50505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610d36610d3183610c39565b610d03565b9050919050565b600060ff82169050919050565b6000610d5d610d5883610c39565b610d3d565b9050919050565b60008160081c9050919050565b6000610d84610d7f83610d64565b610d3d565b9050919050565b600061014083016000808401549050610da381610c50565b610db060008701826108ac565b50600184018583036020870152610dc78382610c7f565b925050600284018583036040870152610de08382610c7f565b925050600384018583036060870152610df98382610c7f565b92505060048401549050610e0c81610d23565b610e19608087018261097d565b5060058401549050610e2a81610d23565b610e3760a087018261097d565b5060068401549050610e4881610c50565b610e5560c08701826108ac565b5060078401549050610e6681610c50565b610e7360e08701826108ac565b5060088401549050610e8481610d4a565b610e92610100870182610998565b50610e9c81610d71565b610eaa610120870182610998565b50819250505092915050565b60006020820190508181036000830152610ed08184610d8b565b90509291505056fea2646970667358221220a613046b9386efb869979a9462d8d0bd900e7b7a65406579143142383f7da8cf64736f6c63430008130033";

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
