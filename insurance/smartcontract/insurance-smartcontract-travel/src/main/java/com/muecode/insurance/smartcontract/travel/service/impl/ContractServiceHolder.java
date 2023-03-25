package com.muecode.insurance.smartcontract.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import com.muecode.insurance.smartcontract.travel.service.ContractService;

@Service
public class ContractServiceHolder implements ContractService {

  @Autowired
  private ConfigurableApplicationContext context;

  private static String contractAddress = "";

  public static String getContractAddress() throws Exception {
    return contractAddress;
  }

  public static void setContractAddress(String address) throws Exception {
    contractAddress = address;
  }

  public void refresh() throws Exception {
    context.refresh();
  }

}
