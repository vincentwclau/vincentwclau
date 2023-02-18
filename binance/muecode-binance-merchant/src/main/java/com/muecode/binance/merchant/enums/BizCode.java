package com.muecode.binance.merchant.enums;

import lombok.Getter;

/**
 * All Business Code
 */
@Getter
public enum BizCode {
  // Success
  SUCCESS(20000, "Success"), //
  // Common Runtime Exception
  NPE_RUNTIME(20001, "Null Pointer Exception - Runtime Exception"), //
  IAE_RUNTIME(20002, "Illegal Arguement Exception - Runtime Exception"), //
  ISE_RUNTIME(20003, "Illegal State Exception - Runtime Exception"), //
  ATE_RUNTIME(20004, "Arithmetic Exception - Runtime Exception"), //
  // API Generic Exception
  REST_CLIENT_EXCEPTION(80001, "ApiException - RestClientException"), //
  UNCATCHED_API_EXCEPTION(89999, "Uncatched API Exception"), //
  // Unhandled and uncatched Exception
  UNCATCHED_RUNTIME_EXCEPTION(99998, "Uncatched Runtime Exception"), //
  UNCATCHED_EXCEPTION(99999, "Uncatched Exception"), //
  ;

  private int code;
  private String desc;
  private String action;

  BizCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  BizCode(int code, String desc, String action) {
    this.code = code;
    this.desc = desc;
    this.action = action;
  }

}
