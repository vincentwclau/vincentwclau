package com.muecode.binance.merchant.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.muecode.binance.merchant.controller.CryptoOperations;
import com.muecode.binance.merchant.foundation.enums.MueBizCode;
import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import com.muecode.binance.merchant.foundation.response.MueResponse;
import com.muecode.binance.merchant.model.dto.CryptoExchangeRespDto;
import com.muecode.binance.merchant.service.CryptoService;

@RestController
@RequestMapping(value = "/crypto/api/v1")
public class CryptoController implements CryptoOperations {

  @Autowired
  private CryptoService cryptoService;

  @Override
  public ResponseEntity<MueResponse<CryptoExchangeRespDto>> quote(MueCurrency sellCurr,
      MueCurrency buyCurr) {
    CryptoExchangeRespDto orderCreateResponse = cryptoService.quote(sellCurr, buyCurr);
    return MueResponse.responseEntity(HttpStatus.OK, MueBizCode.SUCCESS, orderCreateResponse);
  }

}
