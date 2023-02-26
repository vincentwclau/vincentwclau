package com.muecode.binance.merchant.model;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderQueryResponse implements Serializable {
  private String status;
  private String code;
  @JsonProperty(value = "data")
  private OrderQueryResult orderQueryresult;
  private String errorMessage;

  @Getter
  public static class OrderQueryResult implements Serializable {
    private String merchantId;
    private String prepayId;
    private String transactionId;
    private String merchantTradeNo;
    private String tradeType;

    private String status;
    private String currency;
    private BigDecimal orderAmount;
    private String openUserId;
    private String passThroughInfo;
    private long transactTime;
    private long createTime;
  }
}