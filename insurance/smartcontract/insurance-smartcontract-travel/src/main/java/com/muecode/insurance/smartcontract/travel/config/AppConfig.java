package com.muecode.insurance.smartcontract.travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muecode.insurance.smartcontract.travel.foundation.common.MueApi;
import com.muecode.insurance.smartcontract.travel.foundation.common.MueObjectMapper;

@Configuration
public class AppConfig {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public MueObjectMapper getMueObjectMapper(ObjectMapper objectmapper) {
    return new MueObjectMapper(objectmapper);
  }

  @Bean
  public MueApi getMueApi(RestTemplate restTemplate) {
    return new MueApi(restTemplate);
  }

}
