package com.muecode.insurance.smartcontract.travel.foundation.common;

/**
 * Mue of Conversion, e.g. bytes array to Hex.
 * 
 * @author vincent.lau
 */
public final class MueConv {

  public static String toHexString(byte[] bytes) {
    StringBuilder result = new StringBuilder();
    for (byte i : bytes) {
      int decimal = (int) i & 0XFF;
      String hex = Integer.toHexString(decimal);
      if (hex.length() % 2 == 1) {
        hex = new StringBuilder("0").append(hex).toString();
      }
      result.append(hex);
    }
    return result.toString();
  }

}
