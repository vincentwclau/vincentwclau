package com.muecode.binance.merchant.foundation.enums;

public enum MueCurrency {
  // Traditional Currency
  USD(1, "USD", "US Dollar"), //
  HKD(1, "HKD", "Hong Kong Dollar"), //
  CNY(1, "CNY", "CNY"), //
  // Crypto
  USDT(100, "USDT", "Tether"), //
  BTC(101, "BTC", "Bitcoin"), //
  ETH(102, "ETH", "Ethereum"), //
  ;

  private final int code;
  private final String abbr;
  private final String desc;

  private MueCurrency(int code, String abbr, String desc) {
    this.code = code;
    this.abbr = abbr;
    this.desc = desc;
  }

  public int code() {
    return this.code;
  }

  public String abbr() {
    return this.abbr;
  }

  public String desc() {
    return this.desc;
  }

  public static MueCurrency valueByCode(final int code) {
    for (MueCurrency crypto : MueCurrency.values()) {
      if (code == crypto.code) {
        return crypto;
      }
    }
    return null; // this line works if nothing is found.
  }

  public static MueCurrency valueByAbbr(final String abbr) {
    for (MueCurrency crypto : MueCurrency.values()) {
      if (crypto.abbr.equals(abbr)) {
        return crypto;
      }
    }
    return null; // this line works if nothing is found.
  }
}
