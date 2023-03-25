package com.muecode.insurance.smartcontract.travel.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.muecode.insurance.smartcontract.travel.controller.ClaimOperations;
import com.muecode.insurance.smartcontract.travel.foundation.enums.MueBizCode;
import com.muecode.insurance.smartcontract.travel.foundation.response.MueResponse;
import com.muecode.insurance.smartcontract.travel.model.FlightClaimsRespDto;
import com.muecode.insurance.smartcontract.travel.service.ClaimService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/insur/sc/travel/api/v1")
@Slf4j
public class ClaimController implements ClaimOperations {

  @Autowired
  private ClaimService claimService;

  @Override
  public ResponseEntity<MueResponse<List<FlightClaimsRespDto>>> claim(String flightNumber)
      throws Exception {
    log.info("Start claimService.claim()");
    // List<FlightClaimsRespDto> transactionReceipts = claimService.claim(flightNumber,
    // departureTime);
    List<FlightClaimsRespDto> transactionReceipts = claimService.claim(flightNumber);
    log.info("Call claimService.claim, transactionReceipts={}", transactionReceipts);
    return MueResponse.responseEntity(HttpStatus.OK, MueBizCode.SUCCESS, transactionReceipts);
  }

}
