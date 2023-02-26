package com.muecode.binance.merchant.model;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CryptoExchangeResponse implements Serializable {
  @JsonProperty(value = "lprice")
  BigDecimal price;
  @JsonProperty(value = "curr1")
  MueCurrency sellCurr;
  @JsonProperty(value = "curr2")
  MueCurrency buyCurr;
}
