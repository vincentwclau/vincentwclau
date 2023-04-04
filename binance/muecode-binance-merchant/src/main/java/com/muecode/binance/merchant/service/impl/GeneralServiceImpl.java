package com.muecode.binance.merchant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import com.muecode.binance.merchant.foundation.common.MueApi;
import com.muecode.binance.merchant.model.ServerTimeResponse;
import com.muecode.binance.merchant.service.GeneralService;

@Service
public class GeneralServiceImpl implements GeneralService {

  @Autowired
  private MueApi mueApi;

  @Value("${binance.domain}")
  private String binanceDomain;

  @Value("${binance.services.server-time.version}")
  private String serverTimeServiceVersion;

  @Value("${binance.services.server-time.resource}")
  private String serverTimeServiceResource;


  @Override
  public ServerTimeResponse getBinaceServerTime() {
    return mueApi.exchange(HttpMethod.GET, binanceDomain, serverTimeServiceVersion,
        serverTimeServiceResource, null, null, null, ServerTimeResponse.class).getBody();
  }

  
}
