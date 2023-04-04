package com.muecode.binance.merchant.model.dto;

import java.util.Map;
import java.util.Queue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import com.muecode.binance.merchant.foundation.response.MueResponse;
import com.muecode.binance.merchant.model.OrderQueryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderHealthCheckDto {
  Queue<OrderQueryResponse> orderQueue;
  Map<String, OrderQueryResponse> orderChangeMap;
  Map<String, DeferredResult<ResponseEntity<MueResponse<OrderQueryResponse>>>> deferedResultMap;
}
