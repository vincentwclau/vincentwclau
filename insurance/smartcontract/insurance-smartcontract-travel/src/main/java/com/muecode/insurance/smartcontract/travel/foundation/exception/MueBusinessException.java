package com.muecode.insurance.smartcontract.travel.foundation.exception;

import com.muecode.insurance.smartcontract.travel.foundation.enums.MueBizCode;
import lombok.Getter;

@Getter
public class MueBusinessException extends Exception {

  private String code;

  public MueBusinessException() {}

  public MueBusinessException(MueBizCode bizcode) {
    super(bizcode.getAction() == null ? //
        bizcode.getDesc() : bizcode.getDesc() + " " + bizcode.getAction());
    this.code = bizcode.getCode();
  }

  public MueBusinessException(MueBizCode bizcode, String overrideMessage) {
    super(overrideMessage);
    this.code = bizcode.getCode();
  }

  public static MueBusinessException of(MueBizCode bizcode) {
    return new MueBusinessException(bizcode);
  }

  public static MueBusinessException of(MueBizCode bizcode, String overrideMessage) {
    return new MueBusinessException(bizcode, overrideMessage);
  }

}
