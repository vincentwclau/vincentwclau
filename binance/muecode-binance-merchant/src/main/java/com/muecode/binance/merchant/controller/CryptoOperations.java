package com.muecode.binance.merchant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import com.muecode.binance.merchant.foundation.response.MueResponse;
import com.muecode.binance.merchant.model.dto.CryptoExchangeRespDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping(value = "/default")
public interface CryptoOperations {
  /**
   * Quote Crypto Exchange Rate. i.e. Sell USDT, Buy USD.
   * 
   * @param sellCurr Sell Currency {@link} MueCurrency.
   * @param buyCurr Buy Currency {@link} MueCurrency.
   * @return MueResponse<CryptoExchangeRespDto> {@link} CryptoExchangeRespDto.
   */
  @Operation(summary = "Quote Crypto Exchange Rate. i.e. Sell USDT, Buy USD", parameters = { //
      @Parameter(name = "sellcurr", in = ParameterIn.PATH, description = "Sell Currency",
          required = true), //
      @Parameter(name = "buycurr", in = ParameterIn.PATH, description = "Buy Currency",
          required = true) //
  }, responses = { //
      @ApiResponse(responseCode = "200",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = MueResponse.class))),
      @ApiResponse(responseCode = "400", //
          content = @Content(mediaType = "application/json", //
              schema = @Schema(implementation = MueResponse.class))), //
  })
  @GetMapping(value = "/quote/sell/{sellcurr}/buy/{buycurr}")
  public ResponseEntity<MueResponse<CryptoExchangeRespDto>> quote(
      @PathVariable(name = "sellcurr") MueCurrency sellCurr,
      @PathVariable(name = "buycurr") MueCurrency buyCurr //
  );

}
