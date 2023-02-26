package com.muecode.binance.merchant.model.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCreateRespDto implements Serializable {
  private String merchantTradeNo;
  private String prepayId;
  private String status;
  private String code;
  private String errorMessage;
  private String terminalType;
  private long expireUnixTime;
  private String qrcodeLink;
  private String qrContent;
  private String checkoutUrl;
  private String deeplink;
  private String universalUrl;
}
