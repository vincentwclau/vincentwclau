package com.muecode.binance.merchant.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.muecode.binance.merchant.enums.BizCode;
import com.muecode.binance.merchant.exception.ApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public final class ApiUtil {

  @Autowired
  RestTemplate restTemplate;

  /*
   * HttpHeaders headers = new HttpHeaders(); headers.add("token", "123"); ResponseEntity<UserBean>
   * response = restTemplate.exchange( "http://127.0.0.1:8280/user/{id}", HttpMethod.GET, new
   * HttpEntity<String>(headers), UserBean.class, userId);
   */
  public <T extends Object> ResponseEntity<T> exchange(String baseUrl, String serviceVers, //
      String serviceUrl, HashMap<String, String> queryParms, HttpMethod httpMethod, //
      HttpHeaders headers, Class<T> returnType) throws ApiException {
    try {
      // Prepare URI
      URI uri = prepareUrlAndQueryParams(baseUrl, serviceVers, serviceUrl, queryParms);
      HttpEntity<String> httpEntity = new HttpEntity<>(headers);
      // Make Request by exchange method
      return restTemplate.exchange(uri, httpMethod, httpEntity, returnType);
    } catch (RestClientException e) {
      e.printStackTrace();
      throw ApiException.of(BizCode.REST_CLIENT_EXCEPTION);
    } catch (Exception e) {
      e.printStackTrace();
      throw ApiException.of(BizCode.UNCATCHED_API_EXCEPTION,
          BizCode.UNCATCHED_API_EXCEPTION.getDesc() + " " + e.getMessage());
    }
  }

  public <T extends Object> ResponseEntity<T> getForEntity(String baseUrl, String serviceVers,
      String serviceUrl, HashMap<String, String> queryParms, Class<T> returnType)
      throws ApiException {
    try {
      // Prepare URI
      URI uri = prepareUrlAndQueryParams(baseUrl, serviceVers, serviceUrl, queryParms);
      // Make Get Request
      return restTemplate.getForEntity(uri, returnType);
    } catch (RestClientException e) {
      e.printStackTrace();
      throw ApiException.of(BizCode.REST_CLIENT_EXCEPTION);
    } catch (Exception e) {
      e.printStackTrace();
      throw ApiException.of(BizCode.UNCATCHED_API_EXCEPTION,
          BizCode.UNCATCHED_API_EXCEPTION.getDesc() + " " + e.getMessage());
    }
  }

  public <T extends Object> ResponseEntity<T> postForEntity(String baseUrl, String serviceVers,
      String serviceUrl, Object requestObject, HashMap<String, String> queryParms,
      Class<T> returnType) throws ApiException {
    try {
      // Prepare URI
      URI uri = prepareUrlAndQueryParams(baseUrl, serviceVers, serviceUrl, queryParms);
      // Make Post Request
      return restTemplate.postForEntity(uri, requestObject, returnType);
    } catch (RestClientException e) {
      e.printStackTrace();
      throw ApiException.of(BizCode.REST_CLIENT_EXCEPTION);
    } catch (Exception e) {
      e.printStackTrace();
      throw ApiException.of(BizCode.UNCATCHED_API_EXCEPTION,
          BizCode.UNCATCHED_API_EXCEPTION.getDesc() + " " + e.getMessage());
    }
  }

  public static URI prepareUrlAndQueryParams(String baseUrl, String serviceVers, String serviceUrl,
      HashMap<String, String> queryParms) {
    UriComponentsBuilder url =
        UriComponentsBuilder.fromUriString(baseUrl).pathSegment(serviceVers).path(serviceUrl);
    // Adding query parameters
    for (Map.Entry<String, String> entry : queryParms.entrySet()) {
      url = url.queryParam(entry.getKey(), entry.getValue());
    }
    // To URI
    URI uri = url.build().toUri();
    log.info("url={}", uri.toString());
    return uri;
  }

}
