package com.muecode.binance.merchant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.muecode.binance.merchant.BinanceMerchantApplication;
import com.muecode.binance.merchant.service.MerchantOrderService;
import com.muecode.binance.merchant.service.impl.MerchantOrderServiceHolder;

@Component
public class ScheduledTask {

  @Autowired
  MerchantOrderService merchantOrderService;

  @Autowired
  BinanceMerchantApplication myApp;

  /**
   * Execute every second. 0/1 * * * * MON-SUN. <minute> <hour> <day-of-month> <month> <day-of-week>
   */
  @Scheduled(cron = "${scheduled.query-order.cron}", zone = "${scheduled.timezone}")
  public void queryOrder() throws InterruptedException {
    merchantOrderService.syncOrders();
  }

  /**
   * Execute every second. 0 0 0/3 * * MON-SUN. <minute> <hour> <day-of-month> <month> <day-of-week>
   */
  @Scheduled(cron = "${scheduled.clear-order-from-map.cron}", zone = "${scheduled.timezone}")
  public void clearOrderFromMap() throws InterruptedException {
    MerchantOrderServiceHolder.clearOrderFromMap();
  }

  @Scheduled(cron = "${scheduled.app-restart.cron}", zone = "${scheduled.timezone}")
  public void restart() throws InterruptedException {
    myApp.restart();
  }

}
