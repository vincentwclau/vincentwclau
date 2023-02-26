package com.muecode.binance.merchant.model.dto;

import java.util.Map;
import java.util.Queue;
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
}
