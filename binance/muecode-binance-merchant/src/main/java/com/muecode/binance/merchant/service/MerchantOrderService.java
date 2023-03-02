package com.muecode.binance.merchant.service;

import java.math.BigDecimal;
import java.util.List;
import com.muecode.binance.merchant.enums.OrderTerminalType;
import com.muecode.binance.merchant.foundation.enums.MueCurrency;
import com.muecode.binance.merchant.model.OrderCreateResponse;
import com.muecode.binance.merchant.model.dto.OrderHealthCheckDto;

public interface MerchantOrderService {

  public OrderCreateResponse createOrder(OrderTerminalType terminalType, BigDecimal orderAmount,
      MueCurrency currency, List<MueCurrency> supportPayCurrency, long expireTime);

  public void syncOrders();

  public OrderHealthCheckDto getOrderStatus();

}
