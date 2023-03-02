package com.muecode.binance.merchant.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import com.muecode.binance.merchant.foundation.response.MueResponse;
import com.muecode.binance.merchant.model.OrderCreateResponse;
import com.muecode.binance.merchant.model.OrderQueryResponse;
import com.muecode.binance.merchant.model.dto.OrderCreateRequestDto;
import com.muecode.binance.merchant.model.dto.OrderHealthCheckDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RequestMapping(value = "/default")
public interface MerchantOrderOperations {

  /**
   * Service endpoint to request an order from Binance Merchant Pay.
   * http://localhost:8080/binance/merchant/api/v1/order
   * 
   * @param request {@link} OrderCreateRequestDto
   * @return {@link} OrderCreateResponse
   */
  @Operation(summary = "Create Order(QR Code) in Binance. Return Prepay-ID as key for later query.",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Create Order"),
      responses = { //
          @ApiResponse(responseCode = "200", //
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MueResponse.class))),
          @ApiResponse(responseCode = "400", //
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MueResponse.class))) //
      })
  @PostMapping(value = "/order")
  public ResponseEntity<MueResponse<OrderCreateResponse>> createOrder(
      @Valid @RequestBody OrderCreateRequestDto request);

  /**
   * Query New Order Status. Long Polling by DeferredResult Implementation.
   * http://localhost:8080/binance/merchant/api/v1/order/prepay-id/213687184409788416
   * 
   * @param prepayId A Prepay ID identify the order created in Binance
   * @param pollingTimeout long polling timeout parameter (in ms)
   * @return Return DeferredResult<OrderQueryResponse> where there is updated order status
   */
  @Operation(summary = "Long Polling Order Status by Prepay-ID. Return if order status changed.",
      parameters = { //
          @Parameter(name = "prepayid", in = ParameterIn.PATH, description = "Prepay ID",
              content = @Content(mediaType = "application/json", //
                  schema = @Schema(example = "")),
              required = true), //
          @Parameter(name = "pollingTimeout", in = ParameterIn.QUERY,
              description = "Polling Timeout(ms)",
              content = @Content(mediaType = "application/json", //
                  schema = @Schema(example = "120000")),
              required = false) //
      }, responses = { //
          @ApiResponse(responseCode = "200",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MueResponse.class))),
          @ApiResponse(responseCode = "400",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MueResponse.class))), //
          @ApiResponse(responseCode = "408", //
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MueResponse.class))) //
      })
  @GetMapping(value = "/order/prepay-id/{prepayid}")
  public DeferredResult<ResponseEntity<MueResponse<OrderQueryResponse>>> longPollingOrderStatus(
      @PathVariable(name = "prepayid") String prepayId, //
      @RequestParam(name = "pollingTimeout", required = false) Optional<Long> pollingTimeout);

  /**
   * For Internal Health Check only. Not for client side use.
   * http://localhost:8080/binance/merchant/api/v1/order-results
   * 
   * @return Return static Hashmap storing the order result set
   */
  @Hidden
  @Operation(summary = "This method is used to internlly health check only.")
  @GetMapping(value = "/order-results")
  public ResponseEntity<OrderHealthCheckDto> getOrderResult();

}
