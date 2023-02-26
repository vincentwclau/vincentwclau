package com.muecode.binance.merchant.enums;

public enum OrderStatus {
  // The transaction has been initiated.
  // Return this status after calling “Create Order” API successfully.
  INITIAL, //
  // The transaction is pending for payment.
  PENDING, //
  // The transaction has been paid.
  PAID, //
  // The transaction has been closed by you with the close order API.
  CANCELED, //
  // There is an error occurred during the transaction.
  ERROR, //
  // The transaction is under a refund process.
  REFUNDING, //
  // The transaction is under a refund process.
  REFUNDED, //
  // The transaction is expired. By default, the QR code will expire after 1 hour.
  EXPIRED, //
  ;
}
