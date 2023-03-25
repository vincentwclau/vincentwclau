package com.muecode.insurance.smartcontract.travel.foundation.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muecode.insurance.smartcontract.travel.foundation.enums.MueBizCode;
import com.muecode.insurance.smartcontract.travel.foundation.exception.MueRuetimeException;

/**
 * Mue of ObjectMapper, IOC, RunetimeException.
 * 
 * @author vincent.lau
 */
public class MueObjectMapper {

  private ObjectMapper objectmapper;

  public MueObjectMapper(ObjectMapper objectmapper) {
    this.objectmapper = objectmapper;
  }

  public String writeValueAsString(Object object) {
    try {
      // mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
      return this.objectmapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw MueRuetimeException.of(MueBizCode.JSON_PROCESSING_EXCEPTION);
    }
  }

}
