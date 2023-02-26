package com.muecode.binance.merchant.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CryptoExchangeRespDto implements Serializable {
  MueCurrency buyCurr;
  MueCurrency sellCurr;
  BigDecimal price;
  UUID quoteId;
}
