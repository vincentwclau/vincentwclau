package com.muecode.binance.merchant.model.dto;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.muecode.binance.merchant.enums.OrderTerminalType;
import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCreateRequestDto {
  /* Optional. Default WEB. Possible values: WEB, APP, etc. */
  OrderTerminalType terminalType;
  /* Required. */
  @NotNull
  BigDecimal orderAmount;
  /* Optional. Currently support USDT only. */
  MueCurrency currency;
  /* Optional. Default USDT. Can be Multiple Values: USDT,BTC,ETH */
  List<MueCurrency> supportPayCurrency;
  /* Optional. Default 120000, 2 mins. */
  Long expireTime;
}
