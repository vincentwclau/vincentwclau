package com.muecode.insurance.smartcontract.travel.config;

import java.io.IOException;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SimpleCORSFilter implements Filter {

  @Override
  public void doFilter(jakarta.servlet.ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    jakarta.servlet.http.HttpServletRequest reqs = (HttpServletRequest) req;
    String curOrigin = reqs.getHeader("Origin");
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", curOrigin == null ? "true" : curOrigin);
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers",
        "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
    response.setContentType("application/json;charset=UTF-8");
    chain.doFilter(req, res);
  }

  public void init(FilterConfig filterConfig) {}

  public void destroy() {}
}
