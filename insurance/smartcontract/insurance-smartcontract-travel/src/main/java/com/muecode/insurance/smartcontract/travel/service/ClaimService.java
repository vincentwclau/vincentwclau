package com.muecode.insurance.smartcontract.travel.service;

import java.util.List;
import com.muecode.insurance.smartcontract.travel.model.FlightClaimsRespDto;

public interface ClaimService {

  List<FlightClaimsRespDto> claim(String flightNumber) throws Exception;

}
