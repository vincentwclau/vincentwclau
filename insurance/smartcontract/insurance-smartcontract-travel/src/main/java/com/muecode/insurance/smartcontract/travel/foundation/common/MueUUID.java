package com.muecode.insurance.smartcontract.travel.foundation.common;

import java.util.UUID;

/**
 * Mue of UUID.
 * 
 * @author vincent.lau
 */
public final class MueUUID {

  private UUID uuid;

  private MueUUID(UUID uuid) {
    this.uuid = uuid;
  }

  /**
   * Static method of UUID.randomUUID() without hyphens, e.g. from
   * 40793799-ace2-49e4-8330-4a908bf60e1d to 40793799ace249e483304a908bf60e1d
   * 
   * @return String of UUID without hyphens
   */
  public String toStringIgnoreHyphens() {
    return this.uuid.toString().replace("-", "");
  }

  /**
   * Static method of UUID.randomUUID()
   * 
   * @return String of UUID without hyphens
   */
  public static MueUUID randomUUID() {
    return new MueUUID(UUID.randomUUID());
  }

  public UUID uuid() {
    return this.uuid;
  }

}
