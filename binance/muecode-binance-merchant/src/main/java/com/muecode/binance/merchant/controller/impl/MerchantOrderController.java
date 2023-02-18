package com.muecode.binance.merchant.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muecode.binance.merchant.controller.MerchantOrderOperations;

@RestController
@RequestMapping(value = "/api/v1")
public class MerchantOrderController implements MerchantOrderOperations {

  @Override
  public String sayHello() {
    return "Hello Wolrd!";
  }

}
