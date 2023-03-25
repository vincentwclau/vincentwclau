package com.muecode.insurance.smartcontract.travel.foundation.common;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * MACA: Message Authentication Code Algorithm.
 * 
 * @author vincent.lau
 */
public final class MueMac {
  /**
   * HmacSha256 Encryption.
   * 
   * @param message The message going to encrypt by the specified algorithm
   * @param secret secret key, usaully the api secret
   * @return Encrypted Message Body in HexString
   */
  public static String encrpytToHexString(String algorithm, String secret, String payload) {
    try {
      Mac mac = Mac.getInstance(algorithm);
      SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), algorithm);
      mac.init(secretKeySpec);
      byte[] bytes = mac.doFinal(payload.getBytes("UTF-8"));
      return MueConv.toHexString(bytes);
    } catch (Exception e) { // Create custom BusinessException Later
      System.out.println("Error in Encryption");
      return "";
    }
  }

}
