package com.muecode.insurance.smartcontract.travel.config;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import com.muecode.insurance.smartcontract.travel.contract.TravelInsuranceFactory;
import com.muecode.insurance.smartcontract.travel.service.impl.ContractServiceHolder;

@Configuration
public class Web3jConfig {

  @Value("${ethereum.node-url}")
  private String nodeUrl;

  @Value("${ethereum.credentials.private-key}")
  private String privateKey;

  @Value("${ethereum.contract.address}")
  private String contractAddress;

  @Value("${ethereum.contract.gas-price}")
  private BigInteger gasPrice;

  @Value("${ethereum.contract.gas-limit}")
  private BigInteger gasLimit;

  @Bean
  public Web3j web3j() {
    return Web3j.build(new HttpService(nodeUrl));
  }

  @Bean
  public Credentials credentials() {
    return Credentials.create(privateKey);
  }

  @Bean
  public ContractGasProvider contractGasProvider() {
    return new StaticGasProvider(gasPrice, gasLimit);
  }

  @Bean
  public TravelInsuranceFactory travelInsuranceFactory(Web3j web3j, Credentials credentials,
      ContractGasProvider contractGasProvider) throws Exception {
    ContractServiceHolder.setContractAddress(contractAddress);
    return TravelInsuranceFactory.load(contractAddress, web3j, credentials, contractGasProvider);
  }

}
