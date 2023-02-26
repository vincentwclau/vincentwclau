package com.muecode.binance.merchant.foundation.common;

import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.muecode.binance.merchant.foundation.enums.MueBizCode;
import com.muecode.binance.merchant.foundation.exception.MueRuetimeException;
import lombok.extern.slf4j.Slf4j;

/**
 * Mue of Api (mainly for RestTemplate).
 * 
 * @author vincent.lau
 */
@Slf4j
public final class MueApi {

  private RestTemplate restTemplate;

  public MueApi(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /*
   * HttpHeaders headers = new HttpHeaders(); headers.add("token", "123"); ResponseEntity<UserBean>
   * response = restTemplate.exchange( "http://127.0.0.1:8280/user/{id}", HttpMethod.GET, new
   * HttpEntity<String>(headers), UserBean.class, userId);
   */
  public <T extends Object> ResponseEntity<T> exchange(HttpMethod httpMethod, String domain,
      String version, String resource, MultiValueMap<String, String> headerMap,
      MultiValueMap<String, String> queryParms, Object requestBody, Class<T> returnType) {
    try {
      // Prepare URI
      URI uri = computeUrl(domain, version, resource, queryParms);
      // Prepare HttpHeaders and httpsEntity
      HttpHeaders httpHeaders = computeHttpHeaders(headerMap);
      HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders); // Default GET
      if (requestBody != null) {
        httpEntity = new HttpEntity<>(requestBody, httpHeaders);
      }
      // Make Request by exchange method
      log.info("uri={}", uri);

      return restTemplate.exchange(uri, httpMethod, httpEntity, returnType);
    } catch (RestClientException e) {
      e.printStackTrace();
      throw MueRuetimeException.of(MueBizCode.REST_CLIENT_EXCEPTION, e.getMessage());
    }
  }

  public <T extends Object> ResponseEntity<T> getForEntity(String domain, String version,
      String resource, MultiValueMap<String, String> queryParms, Class<T> returnType) {
    try {
      // Prepare URI
      URI uri = computeUrl(domain, version, resource, queryParms);
      // Make Get Request
      return restTemplate.getForEntity(uri, returnType);
    } catch (RestClientException e) {
      e.printStackTrace();
      throw MueRuetimeException.of(MueBizCode.REST_CLIENT_EXCEPTION, e.getMessage());
    }
  }

  public <T extends Object> ResponseEntity<T> postForEntity(String domain, String version,
      String resource, Object requestObject, MultiValueMap<String, String> queryParms,
      Class<T> returnType) {
    try {
      // Prepare URI
      URI uri = computeUrl(domain, version, resource, queryParms);
      // Make Post Request
      return restTemplate.postForEntity(uri, requestObject, returnType);
    } catch (RestClientException e) {
      e.printStackTrace();
      throw MueRuetimeException.of(MueBizCode.REST_CLIENT_EXCEPTION, e.getMessage());
    }
  }

  public static URI computeUrl(String domain, String version, String resource,
      MultiValueMap<String, String> queryParms) {
    URI uri = UriComponentsBuilder.fromUriString(domain) // service domain
        .pathSegment(version) // service endpoint
        .path(resource) // service endpoint
        .queryParams(queryParms) // Adding query parameters
        .build() //
        .toUri();
    // log.info("url={}", uri.toString());
    return uri;
  }

  public static HttpHeaders computeHttpHeaders(MultiValueMap<String, String> headerMap) {
    return headerMap == null ? null : new HttpHeaders(headerMap);
  }

}
