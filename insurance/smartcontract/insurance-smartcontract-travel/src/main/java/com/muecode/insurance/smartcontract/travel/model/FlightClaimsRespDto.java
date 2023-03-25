package com.muecode.insurance.smartcontract.travel.model;

import java.math.BigInteger;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightClaimsRespDto {
  BigInteger planId;
  String planName;
  String flightUid;
  String fromAddress;
  String toAddress;
  Boolean isClaimed;
  BigInteger payoutAmount;
  Boolean isCurrentClaim;
  TransactionReceipt transactionReceipt;
}
