package com.muecode.insurance.smartcontract.travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.muecode.insurance.smartcontract.travel.foundation.enums.MueBizCode;
import com.muecode.insurance.smartcontract.travel.foundation.enums.MueStatus;
import com.muecode.insurance.smartcontract.travel.foundation.exception.MueBusinessException;
import com.muecode.insurance.smartcontract.travel.foundation.exception.MueRuetimeException;
import com.muecode.insurance.smartcontract.travel.foundation.response.MueResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {MueRuetimeException.class})
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public MueResponse<?> handleMueRuetimeException(MueRuetimeException e) {
    return MueResponse.<Object>builder() //
        .status(MueStatus.FAIL) //
        .code(e.getCode()) //
        .message(e.getMessage()) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(value = {MueBusinessException.class})
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public MueResponse<?> handleMueBusinessException(MueBusinessException e) {
    return MueResponse.<Object>builder() //
        .status(MueStatus.FAIL) //
        .code(e.getCode()) //
        .message(e.getMessage()) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(value = {RuntimeException.class})
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public MueResponse<?> handleRuntimeException(RuntimeException e) {
    MueBizCode eCode = getEnum(e);
    String errorMessage = getErrorMessage(eCode, e);
    return MueResponse.<Object>builder() //
        .status(MueStatus.FAIL) //
        .code(eCode.getCode()) //
        .message(errorMessage) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public MueResponse<?> handleException(Exception e) {
    MueBizCode eCode = getEnum(e);
    String errorMessage = getErrorMessage(eCode, e);
    return MueResponse.<Object>builder() //
        .status(MueStatus.FAIL) //
        .code(eCode.getCode()) //
        .message(errorMessage) //
        .data(null) //
        .build();
  }

  private String getErrorMessage(MueBizCode eCode, Exception e) {
    return eCode.getDesc() + ": " + e.getMessage();
  }

  // For both RuntimeException and Exception
  private MueBizCode getEnum(Exception e) {
    if (e instanceof NullPointerException) {
      return MueBizCode.NPE_RUNTIME;
    } else if (e instanceof ArithmeticException) {
      return MueBizCode.ATE_RUNTIME;
    } else if (e instanceof MissingPathVariableException) {
      return MueBizCode.MISSING_PATH_VARIABLE_EXCEPTION;
    } else if (e instanceof RuntimeException) {
      return MueBizCode.UNCATCHED_RUNTIME_EXCEPTION;
    }
    return MueBizCode.UNCATCHED_EXCEPTION;
  }

}
