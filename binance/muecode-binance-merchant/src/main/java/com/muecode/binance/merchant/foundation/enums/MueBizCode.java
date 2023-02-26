package com.muecode.binance.merchant.foundation.enums;

import lombok.Getter;

/**
 * All Business Code
 */
@Getter
public enum MueBizCode {
  // Success
  SUCCESS("000000", "Successfully Processed."), //
  // FAIL
  FAIL("100000", "Fail-General Exception."), //
  LONG_POLLING_TIMEOUT("100001", "Long Polling Timeout."), //
  // Common Runtime Exception
  NPE_RUNTIME("120001", "Null Pointer Exception-RuntimeException."), //
  IAE_RUNTIME("120002", "Illegal Arguement Exception-RuntimeException."), //
  ISE_RUNTIME("120003", "Illegal State Exception-RuntimeException."), //
  ATE_RUNTIME("120004", "Arithmetic Exception-RuntimeException."), //
  // JSON Processing Exception
  JSON_PROCESSING_EXCEPTION("170001", "JsonProcessingException-Exception."), //
  // API Generic Exception
  REST_CLIENT_EXCEPTION("180001", "RestClientException-RuntimeException."), //
  // Web Request Exception
  MISSING_PATH_VARIABLE_EXCEPTION("190001", "Missing Path Variable Exception."), //
  // MueRuntimeException and MueException
  MUE_RUNTIME_EXCEPTION("999996", "Mue Runtime Exception."), //
  MUE_EXCEPTION("999997", "Mue Exception."), //
  // Unhandled and uncatched Exception
  UNCATCHED_RUNTIME_EXCEPTION("999998", "Uncatched Runtime Exception."), //
  UNCATCHED_EXCEPTION("999999", "Uncatched Exception."), //
  ;

  private String code;
  private String desc;
  private String action;

  MueBizCode(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  MueBizCode(String code, String desc, String action) {
    this.code = code;
    this.desc = desc;
    this.action = action;
  }

}
