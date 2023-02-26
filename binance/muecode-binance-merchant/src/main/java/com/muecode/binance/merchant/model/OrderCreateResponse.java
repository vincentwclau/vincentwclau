package com.muecode.binance.merchant.model;

import java.io.Serializable;
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
public class OrderCreateResponse implements Serializable {
  private String status;
  private String code;
  @JsonProperty(value = "data")
  private OrderCreateResult orderQrCodeResult;
  private String errorMessage;

  @Getter
  public static class OrderCreateResult implements Serializable {
    private String prepayId;
    private String terminalType;
    private long expireTime;
    private String qrcodeLink;
    private String qrContent;
    private String checkoutUrl;
    private String deeplink;
    private String universalUrl;
  }
}
