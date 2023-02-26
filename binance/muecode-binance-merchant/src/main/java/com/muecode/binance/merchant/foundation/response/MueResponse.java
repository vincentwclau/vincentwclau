package com.muecode.binance.merchant.foundation.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import com.muecode.binance.merchant.enums.OrderStatus;
import com.muecode.binance.merchant.foundation.enums.MueBizCode;
import com.muecode.binance.merchant.foundation.enums.MueStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MueResponse<T> {
  private MueStatus status;
  private String code;
  private String message;
  private T data;

  public static <T> ResponseEntity<MueResponse<T>> responseEntity(HttpStatusCode hStatusCode,
      MueBizCode mBizCode, T object) {
    MueResponse<T> mueResponse = MueResponse.<T>builder() //
        .status(mBizCode == MueBizCode.SUCCESS ? MueStatus.SUCCESS : MueStatus.FAIL) //
        .code(mBizCode.getCode()) //
        .message(mBizCode.getDesc()) //
        .data(object) //
        .build();
    return ResponseEntity.status(hStatusCode).body(mueResponse);
  }

  public static <T> ResponseEntity<MueResponse<T>> responseEntity(HttpStatusCode hStatusCode,
      MueBizCode mBizCode, T object, String overrideMessage) {

    MueResponse<T> mueResponse = MueResponse.<T>builder() //
        .status(mBizCode == MueBizCode.SUCCESS ? MueStatus.SUCCESS : MueStatus.FAIL) //
        .code(mBizCode.getCode()) //
        .message(overrideMessage) //
        .data(object) //
        .build();
    return ResponseEntity.status(hStatusCode).body(mueResponse);
  }
}
