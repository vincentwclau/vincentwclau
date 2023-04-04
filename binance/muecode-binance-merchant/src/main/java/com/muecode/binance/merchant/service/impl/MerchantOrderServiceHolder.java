package com.muecode.binance.merchant.service.impl;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
import com.muecode.binance.merchant.enums.OrderStatus;
import com.muecode.binance.merchant.enums.OrderTerminalType;
import com.muecode.binance.merchant.foundation.common.MueApi;
import com.muecode.binance.merchant.foundation.common.MueObjectMapper;
import com.muecode.binance.merchant.foundation.common.MueUUID;
import com.muecode.binance.merchant.foundation.domain.MueBinanceMerchant;
import com.muecode.binance.merchant.foundation.enums.MueBizCode;
import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import com.muecode.binance.merchant.foundation.exception.MueRuetimeException;
import com.muecode.binance.merchant.foundation.response.MueResponse;
import com.muecode.binance.merchant.model.OrderCreateRequest;
import com.muecode.binance.merchant.model.OrderCreateResponse;
import com.muecode.binance.merchant.model.OrderQueryRequest;
import com.muecode.binance.merchant.model.OrderQueryResponse;
import com.muecode.binance.merchant.model.dto.OrderHealthCheckDto;
import com.muecode.binance.merchant.service.GeneralService;
import com.muecode.binance.merchant.service.MerchantOrderService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MerchantOrderServiceHolder implements MerchantOrderService {

  private static final Queue<OrderQueryResponse> orderQueue = new ConcurrentLinkedQueue<>();

  private static Map<String, OrderQueryResponse> orderChangeMap = new ConcurrentHashMap<>();

  private static final Map<String, DeferredResult<ResponseEntity<MueResponse<OrderQueryResponse>>>> deferredResultMap =
      new ConcurrentHashMap<>();

  @Autowired
  private MueApi mueApi;

  @Autowired
  private MueObjectMapper mueObjectMapper;

  @Autowired
  private GeneralService generalService;

  @Value("${binance-merchant.domain}")
  private String binanceMerchantDomain;

  @Value("${binance-merchant.services.create-order.version}")
  private String createOrderServiceVersion;

  @Value("${binance-merchant.services.create-order.resource}")
  private String createOrderServiceResource;

  @Value("${binance-merchant.services.query-order.version}")
  private String queryOrderServiceVersion;

  @Value("${binance-merchant.services.query-order.resource}")
  private String queryOrderServiceResource;

  @Autowired
  private MueBinanceMerchant mueBinanceMerchant;

  public OrderHealthCheckDto getOrderStatus() {
    return OrderHealthCheckDto.builder() //
        .orderQueue(orderQueue) //
        .orderChangeMap(orderChangeMap) //
        .deferedResultMap(deferredResultMap) //
        .build();
  }

  public static void putDeferredResultMap(String prepayId,
      DeferredResult<ResponseEntity<MueResponse<OrderQueryResponse>>> deferredResult) {
    deferredResultMap.put(prepayId, deferredResult);
  }

  public static void removeDeferredResultMap(String prepayId) {
    deferredResultMap.remove(prepayId);
  }

  public static OrderQueryResponse getOrderQueryResponse(String prepayId) {
    return orderChangeMap.get(prepayId);
  }

  public static OrderQueryResponse removeOrderQueryResponse(String prepayId) {
    return orderChangeMap.remove(prepayId);
  }

  @Override
  public OrderCreateResponse createOrder(OrderTerminalType terminalType, BigDecimal orderAmount,
      MueCurrency currency, List<MueCurrency> supportPayCurrency, long expireTime) {
    // Get an UUID, ignore hyphens
    String transUuid = MueUUID.randomUUID().toStringIgnoreHyphens();

    // Current Binance Server Time
    long binanceServerTime = generalService.getBinaceServerTime().getServerTime();
    log.info("binance servertime={}", binanceServerTime);
    log.info("current timestamp={}", Instant.now().getEpochSecond());

    // Compute the Comma Separated String
    String supportPayCurrencyCommaSeparated =
        supportPayCurrency.stream().map(MueCurrency::abbr).collect(Collectors.joining(","));

    // Compute the order expiry Unix Timestamp
    long orderExpiryTimeStamp = binanceServerTime + expireTime;

    // Construct the Post Request Body
    OrderCreateRequest.Env env = OrderCreateRequest.Env.builder() //
        .terminalType(terminalType) //
        .build();
    OrderCreateRequest.Goods goods = OrderCreateRequest.Goods.builder() //
        .goodsType("02") //
        .goodsCategory("9000") //
        .goodsName("Life Insurance") //
        .goodsDetail("Life Insurance from ABC Insurance Co.") //
        .referenceGoodsId("7876763A3B") //
        .build();
    OrderCreateRequest request = OrderCreateRequest.builder() //
        .env(env) //
        .goods(goods) //
        .orderAmount(orderAmount) //
        .orderExpireTime(orderExpiryTimeStamp) //
        .merchantTradeNo(transUuid) //
        .currency(currency.abbr()) //
        .supportPayCurrency(supportPayCurrencyCommaSeparated) //
        .build();

    // Serialization by mueObjectMapper
    String requestJsonString = mueObjectMapper.writeValueAsString(request);

    // Compute the Http Headers for order request
    HttpHeaders headers =
        mueBinanceMerchant.computeHttpHeader(transUuid, binanceServerTime, requestJsonString);

    // Invoke Binance Merchant Pay Order Creation API
    OrderCreateResponse orderCreateResponse = mueApi.exchange(HttpMethod.POST, //
        binanceMerchantDomain, createOrderServiceVersion, createOrderServiceResource, headers, null, //
        request, OrderCreateResponse.class) //
        .getBody();

    // if success
    if (orderCreateResponse != null && "SUCCESS".equals(orderCreateResponse.getStatus())
        && "000000".equals(orderCreateResponse.getCode())) {
      // Async method to add queue
      queryOrderNAddToQueue(orderCreateResponse.getOrderQrCodeResult().getPrepayId());
      // Improvement for GC
      headers = null;
      requestJsonString = null;
      request = null;
      goods = null;
      env = null;
      supportPayCurrencyCommaSeparated = null;
      transUuid = null;
      return orderCreateResponse;
    }
    throw MueRuetimeException.of(MueBizCode.REST_CLIENT_EXCEPTION);
  }

  @Override
  public void syncOrders() {

    OrderQueryResponse prevQueryResponse = orderQueue.poll();
    if (prevQueryResponse != null //
    // && !OrderStatus.EXPIRED.name().equals(prevQueryResponse.getOrderQueryresult().getStatus()) //
    ) {
      String prepayId = prevQueryResponse.getOrderQueryresult().getPrepayId();
      log.info("syncOrder, prepayId={}", prepayId);
      // log.info("Queue is with data, prepayId={}", prepayId);
      // Sync Order from Binance
      OrderQueryResponse newQueryResponse = queryOrderFromBinance(prepayId);
      // Error handling
      if (newQueryResponse == null)
        throw MueRuetimeException.of(MueBizCode.REST_CLIENT_EXCEPTION);

      // If status = expired, stop to query order from binance
      // Otherwise, add it back to queue for next query
      if (OrderStatus.INITIAL.name().equals(newQueryResponse.getOrderQueryresult().getStatus()) //
          || OrderStatus.PENDING.name().equals(newQueryResponse.getOrderQueryresult().getStatus()) //
      ) {
        orderQueue.add(newQueryResponse);
      }
      // Update orderChangeMap
      if (!newQueryResponse.getOrderQueryresult().getStatus()
          .equals(prevQueryResponse.getOrderQueryresult().getStatus())) {
        // update orderChangeMap
        orderChangeMap.put(prepayId, newQueryResponse);
      }
      // Check if deferredResultMap contain key (Some threads)
      if (orderChangeMap.containsKey(prepayId) && deferredResultMap.containsKey(prepayId)) {
        // clear orderChangeMap by prepayId
        OrderQueryResponse returnRepsonse = orderChangeMap.remove(prepayId);
        // End Long Polling by set deferred Result
        ResponseEntity<MueResponse<OrderQueryResponse>> deferredResult =
            MueResponse.responseEntity(HttpStatus.OK, MueBizCode.SUCCESS, returnRepsonse);
        deferredResultMap.get(prepayId).setResult(deferredResult);
        // Improvement for GC
        returnRepsonse = null;
        deferredResult = null;
      }
      // Improvement for GC
      newQueryResponse = null;
      prepayId = null;
    }
    // Improvement for GC
    prevQueryResponse = null;
  }

  private OrderQueryResponse queryOrderFromBinance(String prepayId) {
    // Get an UUID, ignore hyphens
    String transUuid = MueUUID.randomUUID().toStringIgnoreHyphens();
    // Current Binance Server Time
    long binanceServerTime = generalService.getBinaceServerTime().getServerTime();
    // Construct request
    OrderQueryRequest request = OrderQueryRequest.builder().prepayId(prepayId).build();
    // Serialization by mueObjectMapper
    String requestJsonString = mueObjectMapper.writeValueAsString(request);
    // Compute the Http Headers for order request
    HttpHeaders headers =
        mueBinanceMerchant.computeHttpHeader(transUuid, binanceServerTime, requestJsonString);
    // Improvement for GC
    transUuid = null;
    requestJsonString = null;
    // Invoke Binanace API
    return mueApi.exchange(HttpMethod.POST, //
        binanceMerchantDomain, queryOrderServiceVersion, //
        queryOrderServiceResource, headers, null, //
        request, OrderQueryResponse.class) //
        .getBody();
  }

  @Async
  private void queryOrderNAddToQueue(String prepayId) {
    orderQueue.add(queryOrderFromBinance(prepayId));
  }

  public static void clearOrderFromMap() {
    log.info("clearOrderFromMap");
    orderChangeMap = orderChangeMap.entrySet().stream() //
        // Filter exist more than 2 mins
        .filter(entry -> entry.getValue().getOrderQueryresult() //
            .getCreateTime() + 120000L >= Instant.now().toEpochMilli())
        .collect(Collectors.toConcurrentMap(entry -> entry.getKey(), entry -> entry.getValue()));
  }

}
