package com.muecode.binance.merchant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.muecode.binance.merchant.service.MerchantOrderService;

@Component
public class ScheduledTask {

  @Autowired
  MerchantOrderService merchantOrderService;

  /**
   * Execute every second, 8 - 4, MON-SUN. 0/1 * * * * MON-SUN.
   */
  @Scheduled(cron = "${scheduled.query-order.cron}", zone = "${scheduled.query-order.timezone}")
  public void queryOrder() throws InterruptedException {
    merchantOrderService.syncOrders();
  }
}
