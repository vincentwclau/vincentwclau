package com.muecode.binance.merchant.exception;

import com.muecode.binance.merchant.enums.BizCode;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

  int code;

  public ApiException() {}

  public ApiException(BizCode bizcode) {
    super(bizcode.getAction() == null ? //
        bizcode.getDesc() : bizcode.getDesc() + " " + bizcode.getAction());
    this.code = bizcode.getCode();
  }

  public ApiException(BizCode bizcode, String overrideMessage) {
    super(overrideMessage);
    this.code = bizcode.getCode();
  }

  public static ApiException of(BizCode bizcode) {
    return new ApiException(bizcode);
  }

  public static ApiException of(BizCode bizcode, String overrideMessage) {
    return new ApiException(bizcode, overrideMessage);
  }

}
