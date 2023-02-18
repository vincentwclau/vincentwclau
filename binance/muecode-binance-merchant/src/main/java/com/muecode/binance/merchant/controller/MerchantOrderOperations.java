package com.muecode.binance.merchant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(value = "/default")
public interface MerchantOrderOperations {

  @GetMapping(value = "/helloworld")
  public String sayHello();

}
