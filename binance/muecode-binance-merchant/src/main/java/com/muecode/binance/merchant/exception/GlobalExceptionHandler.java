package com.muecode.binance.merchant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.muecode.binance.merchant.enums.BizCode;
import com.muecode.binance.merchant.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
  /*
   * @ExceptionHandler(value = { BhException.class })
   * 
   * @ResponseStatus(code = HttpStatus.BAD_REQUEST) public ApiResponse<BhError>
   * handleBhException(BhException e) { return ApiResponse.<BhError>builder() .code(e.getCode())
   * .message(e.getMessage()) .data(e.getBhError()) .build(); }
   */
  @ExceptionHandler(value = {ApiException.class})
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public ApiResponse<?> handleApiException(ApiException e) {
    return ApiResponse.<Object>builder().code(e.getCode()) //
        .message(e.getMessage()) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(value = {NullPointerException.class, ArithmeticException.class})
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public ApiResponse<?> handleRuntimeException(RuntimeException e) {
    BizCode eCode = getEnum(e);
    return ApiResponse.<Object>builder().code(eCode.getCode()).message(eCode.getDesc()).data(null)
        .build();
  }

  @ExceptionHandler(value = {RuntimeException.class})
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public ApiResponse<?> handleUncatchedRuntimeException(RuntimeException e) {
    return ApiResponse.<Object>builder().code(BizCode.UNCATCHED_RUNTIME_EXCEPTION.getCode())
        .message(BizCode.UNCATCHED_RUNTIME_EXCEPTION.getDesc()).data(null).build();
  }

  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public ApiResponse<?> handleUnCatchedException(Exception e) {
    return ApiResponse.<Object>builder().code(BizCode.UNCATCHED_EXCEPTION.getCode())
        .message(BizCode.UNCATCHED_EXCEPTION.getDesc()).data(null).build();
  }

  private BizCode getEnum(RuntimeException e) {
    if (e instanceof NullPointerException) {
      return BizCode.NPE_RUNTIME;
    } else if (e instanceof ArithmeticException) {
      return BizCode.ATE_RUNTIME;
    }
    return BizCode.UNCATCHED_RUNTIME_EXCEPTION;
  }

}
