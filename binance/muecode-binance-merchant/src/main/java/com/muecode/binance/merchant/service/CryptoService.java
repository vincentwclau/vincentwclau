package com.muecode.binance.merchant.service;

import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import com.muecode.binance.merchant.model.dto.CryptoExchangeRespDto;

public interface CryptoService {

  public CryptoExchangeRespDto quote(MueCurrency sellCurr, MueCurrency buyCurr);

}
