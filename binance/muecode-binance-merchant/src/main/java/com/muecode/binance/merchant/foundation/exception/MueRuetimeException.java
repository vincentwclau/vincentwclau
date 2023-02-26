package com.muecode.binance.merchant.foundation.exception;

import com.muecode.binance.merchant.foundation.enums.MueBizCode;
import lombok.Getter;

@Getter
public class MueRuetimeException extends RuntimeException {

  private String code;

  public MueRuetimeException() {}

  public MueRuetimeException(MueBizCode bizcode) {
    super(bizcode.getAction() == null ? //
        bizcode.getDesc() : bizcode.getDesc() + " " + bizcode.getAction());
    this.code = bizcode.getCode();
  }

  public MueRuetimeException(MueBizCode bizcode, String overrideMessage) {
    super(overrideMessage);
    this.code = bizcode.getCode();
  }

  public static MueRuetimeException of(MueBizCode bizcode) {
    return new MueRuetimeException(bizcode);
  }

  public static MueRuetimeException of(MueBizCode bizcode, String overrideMessage) {
    return new MueRuetimeException(bizcode, overrideMessage);
  }

}
