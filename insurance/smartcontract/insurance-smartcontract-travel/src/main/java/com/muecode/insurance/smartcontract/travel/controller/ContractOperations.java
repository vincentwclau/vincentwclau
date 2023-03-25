package com.muecode.insurance.smartcontract.travel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.muecode.insurance.smartcontract.travel.foundation.response.MueResponse;

@RequestMapping(value = "/default")
public interface ContractOperations {

  @GetMapping(value = "/contract/address")
  ResponseEntity<MueResponse<String>> getContractAddress() throws Exception;

  @PostMapping(value = "/contract/address/{address}")
  ResponseEntity<MueResponse<Void>> updateContractAddress(
      @PathVariable(name = "address") String address) throws Exception;

  @PostMapping(value = "/refresh")
  ResponseEntity<MueResponse<Void>> refresh() throws Exception;

}
