package com.muecode.insurance.smartcontract.travel.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.muecode.insurance.smartcontract.travel.controller.ContractOperations;
import com.muecode.insurance.smartcontract.travel.foundation.enums.MueBizCode;
import com.muecode.insurance.smartcontract.travel.foundation.response.MueResponse;
import com.muecode.insurance.smartcontract.travel.service.ContractService;
import com.muecode.insurance.smartcontract.travel.service.impl.ContractServiceHolder;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/insur/sc/travel/api/v1")
@Slf4j
public class ContractController implements ContractOperations {

  @Autowired
  ContractService contractService;

  @Override
  public ResponseEntity<MueResponse<String>> getContractAddress() throws Exception {
    log.info("Start getContractAddress={}");
    String address = ContractServiceHolder.getContractAddress();
    log.info("Result updateContractAddress={}", address);
    return MueResponse.responseEntity(HttpStatus.OK, MueBizCode.SUCCESS, address);
  }

  @Override
  public ResponseEntity<MueResponse<Void>> updateContractAddress(String address) throws Exception {
    log.info("Start updateContractAddress={}", address);
    ContractServiceHolder.setContractAddress(address);
    return MueResponse.responseEntity(HttpStatus.OK, MueBizCode.SUCCESS, null);
  }

  @Override
  public ResponseEntity<MueResponse<Void>> refresh() throws Exception {
    contractService.refresh();
    return MueResponse.responseEntity(HttpStatus.OK, MueBizCode.SUCCESS, null);
  }

}
