package com.muecode.binance.merchant.model;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.muecode.binance.merchant.enums.OrderTerminalType;
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
public class OrderCreateRequest implements Serializable {

  private Env env;
  private String merchantTradeNo; // UUID String32, remove "-"
  private BigDecimal orderAmount;
  private long orderExpireTime; // server time + qrcode expiry time (2min = 120000 ms)
  private String currency; // Update to enum Later, "USDT"
  private String supportPayCurrency; // Update to enum Later, "USDT"
  // String returnUrl;
  // String cancelUrl;
  private Goods goods;

  @Builder
  @Getter
  public static class Goods implements Serializable {
    private String goodsType; // "02"
    private String goodsCategory; // "9000"
    private String referenceGoodsId;
    private String goodsName;
    private String goodsDetail;
  }

  @Builder
  @Getter
  public static class Env implements Serializable {
    private OrderTerminalType terminalType; // WEB
  }

}
