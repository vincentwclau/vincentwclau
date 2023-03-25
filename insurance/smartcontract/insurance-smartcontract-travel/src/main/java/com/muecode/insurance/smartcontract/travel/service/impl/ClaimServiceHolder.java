package com.muecode.insurance.smartcontract.travel.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple10;
import org.web3j.tx.gas.ContractGasProvider;
import com.muecode.insurance.smartcontract.travel.contract.TravelInsurance;
import com.muecode.insurance.smartcontract.travel.contract.TravelInsuranceFactory;
import com.muecode.insurance.smartcontract.travel.model.FlightClaimsRespDto;
import com.muecode.insurance.smartcontract.travel.service.ClaimService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClaimServiceHolder implements ClaimService {

  @Autowired
  private Web3j web3j;

  @Autowired
  private Credentials credentials;

  @Autowired
  private ContractGasProvider contractGasProvider;

  @Autowired
  private TravelInsuranceFactory travelInsuranceFactory;

  // flight id: SQ123 / departureTime: 1678621644
  @Override
  public List<FlightClaimsRespDto> claim(String flightNumber) throws Exception {
    log.info("Start flightNumber={}", flightNumber);
    // Load TravelInsuranceFactory Instance
    // TravelInsuranceFactory travelInsuranceFactory =
    // TravelInsuranceFactory.load(contractAddress, web3j, credentials, contractGasProvider);
    String plane = flightNumber.split("_")[0];
    String departureTime = flightNumber.split("_")[1];

    TravelInsurance travelInsurance = null;
    List<FlightClaimsRespDto> respDtos = new ArrayList<>();
    TransactionReceipt tranReceipt = null;
    for (Object obj : travelInsuranceFactory.getDeployedInsurancesByFlight(plane, departureTime)
        .send()) {
      // Check instanceof before CAST
      if (!(obj instanceof String))
        throw new Exception();
      // Cast String Address of the deployed insurance
      String insuranceAddress = (String) obj;
      // Load TravelInsurance instance
      log.info("Start Claim: insuranceAddress={}", insuranceAddress);
      // Get TravelInsurance Instance
      travelInsurance =
          TravelInsurance.load(insuranceAddress, web3j, credentials, contractGasProvider);
      Tuple10<BigInteger, String, String, String, String, String, BigInteger, BigInteger, Boolean, Boolean> insuranceData =
          travelInsurance.data().send();
      // Check if the claim is paid
      boolean isClaimed = insuranceData.component10();
      boolean isCurrentClaim = false;
      FlightClaimsRespDto respDto = null;

      tranReceipt = null;
      if (!isClaimed) {
        // Get payout amount (wei) from TravelInsurance Instance
        BigInteger payout = insuranceData.component8();
        // Claim Process
        tranReceipt = travelInsurance.claimInsurance(payout).send();
        isCurrentClaim = true;
      }
      // Construct FlightClaimsRespDto for one claim
      respDto = FlightClaimsRespDto.builder() //
          .planId(insuranceData.component1()) //
          .planName(insuranceData.component2()) //
          .fromAddress(insuranceData.component5()) //
          .toAddress(insuranceData.component6()) //
          .flightUid(insuranceData.component3().concat("_".concat(insuranceData.component4()))) //
          .payoutAmount(insuranceData.component8()) //
          .isClaimed(isClaimed) //
          .isCurrentClaim(isCurrentClaim) //
          .transactionReceipt(tranReceipt) //
          .build();
      // Add to claim resuult to response list
      respDtos.add(respDto);
      log.info("End of Claim");
    }
    return respDtos;
  }
}
