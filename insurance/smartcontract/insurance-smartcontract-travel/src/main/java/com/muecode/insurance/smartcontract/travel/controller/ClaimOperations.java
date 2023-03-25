package com.muecode.insurance.smartcontract.travel.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.muecode.insurance.smartcontract.travel.foundation.response.MueResponse;
import com.muecode.insurance.smartcontract.travel.model.FlightClaimsRespDto;

@RequestMapping(value = "/default")
public interface ClaimOperations {

    // SQ123 / departureTime: 1678621644
    // http://localhost:8080/insur/sc/travel/api/v1/claim/flight-no/{SQ123/dept-time/1678621644
    @GetMapping(value = "/claim/flight/{flightNumber}")
    ResponseEntity<MueResponse<List<FlightClaimsRespDto>>> claim( //
            @PathVariable(name = "flightNumber") String flightNumber) throws Exception;

}
