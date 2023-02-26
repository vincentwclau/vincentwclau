package com.muecode.binance.merchant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import com.muecode.binance.merchant.foundation.common.MueApi;
import com.muecode.binance.merchant.foundation.common.MueUUID;
import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import com.muecode.binance.merchant.model.CryptoExchangeResponse;
import com.muecode.binance.merchant.model.dto.CryptoExchangeRespDto;
import com.muecode.binance.merchant.service.CryptoService;

@Service
public class CryptoServiceHolder implements CryptoService {

  @Autowired
  private MueApi mueApi;

  @Value("${crypto.domain}")
  String cryptoExchangeDomain;

  @Value("${crypto.services.exchange.version}")
  String cryptoExchangeVersion;

  @Value("${crypto.services.exchange.resource}")
  String cryptoExchangeResource;

  @Override
  public CryptoExchangeRespDto quote(MueCurrency sellCurr, MueCurrency buyCurr) {
    // Invoke API to get exchange rate
    CryptoExchangeResponse response = exchange(buyCurr, sellCurr);
    // Map Dto
    CryptoExchangeRespDto responseDto = CryptoExchangeRespDto.builder() //
        .buyCurr(MueCurrency.valueOf(response.getBuyCurr())) //
        .sellCurr(MueCurrency.valueOf(response.getSellCurr())) //
        .price(response.getPrice()) //
        .quoteId(MueUUID.randomUUID().uuid()) //
        .build();
    return responseDto;
  }

  private CryptoExchangeResponse exchange(MueCurrency sellCurr, MueCurrency buyCurr) {
    String resourceWithPathVariable =
        cryptoExchangeResource + "/" + sellCurr.name() + "/" + buyCurr.name();
    return mueApi.exchange(HttpMethod.GET, //
        cryptoExchangeDomain, cryptoExchangeVersion, resourceWithPathVariable, null, null, //
        null, CryptoExchangeResponse.class) //
        .getBody();
  }
}
