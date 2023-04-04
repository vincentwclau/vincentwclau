package com.muecode.binance.merchant.controller.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import com.muecode.binance.merchant.controller.MerchantOrderOperations;
import com.muecode.binance.merchant.enums.OrderTerminalType;
import com.muecode.binance.merchant.foundation.enums.MueBizCode;
import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import com.muecode.binance.merchant.foundation.response.MueResponse;
import com.muecode.binance.merchant.model.OrderCreateResponse;
import com.muecode.binance.merchant.model.OrderQueryResponse;
import com.muecode.binance.merchant.model.dto.OrderCreateRequestDto;
import com.muecode.binance.merchant.model.dto.OrderHealthCheckDto;
import com.muecode.binance.merchant.service.MerchantOrderService;
import com.muecode.binance.merchant.service.impl.MerchantOrderServiceHolder;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/binance/merchant/api/v1")
@Slf4j
public class MerchantOrderController implements MerchantOrderOperations {

  @Autowired
  private MerchantOrderService merchantOrderService;

  @Value("${binance-merchant.services.create-order.default-expiry-ms}")
  long defaultExpiryTime;

  @Value("${binance-merchant.services.create-order.default-terminal}")
  String defaultTerminal;

  @Value("${binance-merchant.services.create-order.default-currency}")
  String defaultCurrency;

  @Value("${binance-merchant.services.create-order.default-support-pay-currency}")
  String defaultSupportPayCurrency;

  @Value("${binance-merchant.services.query-order.default-polling-timeout-ms}")
  long defaultPollingTimeout;

  @Override
  public ResponseEntity<MueResponse<OrderCreateResponse>> createOrder(
      OrderCreateRequestDto request) {
    // Handle Default Values for Terminal Type
    OrderTerminalType terminalType =
        request.getTerminalType() == null ? OrderTerminalType.valueOf(defaultTerminal)
            : request.getTerminalType();
    // Handle Default Values for Price Currency
    MueCurrency currencyEnum = request.getCurrency() == null ? MueCurrency.valueOf(defaultCurrency)
        : request.getCurrency();
    // Handle Default Values for Pay Currency
    List<MueCurrency> supportPayCurrencyEnum = request.getSupportPayCurrency() == null
        ? Arrays.asList(MueCurrency.valueOf(defaultSupportPayCurrency))
        : request.getSupportPayCurrency();
    // Handle Default Values for order/ QR code expiry time
    Long expireTimeLong =
        request.getExpireTime() == null ? defaultExpiryTime : request.getExpireTime();
    // Invoke createOrder service
    OrderCreateResponse orderCreateResponse = merchantOrderService.createOrder(terminalType,
        request.getOrderAmount(), currencyEnum, supportPayCurrencyEnum, expireTimeLong);
    return MueResponse.responseEntity(HttpStatus.OK, MueBizCode.SUCCESS, orderCreateResponse);
  }

  @Override
  public DeferredResult<ResponseEntity<MueResponse<OrderQueryResponse>>> longPollingOrderStatus(
      String prepayId, Optional<Long> pollingTimeout) {

    // Client can request a timeout frame, which is should be < order expiry time
    long timeout = pollingTimeout.isPresent() && pollingTimeout.get() < defaultPollingTimeout
        ? pollingTimeout.get()
        : defaultPollingTimeout;

    DeferredResult<ResponseEntity<MueResponse<OrderQueryResponse>>> deferredResult =
        new DeferredResult<>(timeout);

    deferredResult.onCompletion(() -> {
      log.info("Long Polling Order Completed.");
      MerchantOrderServiceHolder.removeDeferredResultMap(prepayId);
    });

    deferredResult.onTimeout(() -> {
      // Timeout Description to return
      log.info("Long Polling Order Timeout");
      String timeoutDesc = "Long Polling Timeout in " + defaultPollingTimeout / 1000L
          + " seconds. You may try again.";
      ResponseEntity<MueResponse<OrderQueryResponse>> deferredResponse = MueResponse.responseEntity(
          HttpStatus.REQUEST_TIMEOUT, MueBizCode.LONG_POLLING_TIMEOUT, null, timeoutDesc);
      deferredResult.setResult(deferredResponse);
      log.info("Long Polling Order onTimeout, removeDeferredResultMap");
      MerchantOrderServiceHolder.removeDeferredResultMap(prepayId);
    });

    MerchantOrderServiceHolder.putDeferredResultMap(prepayId, deferredResult);
    OrderQueryResponse orderQueryResponse =
        MerchantOrderServiceHolder.removeOrderQueryResponse(prepayId);

    // Return directly, if already know there is an updated status
    if (orderQueryResponse != null) {
      ResponseEntity<MueResponse<OrderQueryResponse>> oResponseEntity =
          MueResponse.responseEntity(HttpStatus.OK, MueBizCode.SUCCESS, orderQueryResponse);
      // MerchantOrderServiceHolder.getDeferredResultMap().put(prepayId, deferredResult);
      deferredResult.setResult(oResponseEntity);
    }
    return deferredResult;
  }

  @Override
  public ResponseEntity<OrderHealthCheckDto> getOrderResult() {
    return ResponseEntity.ok().body(merchantOrderService.getOrderStatus());
  }

}
