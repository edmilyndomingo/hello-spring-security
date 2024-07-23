package com.ejd.hellospringsecurity.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class RequestValidationBeforeFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String header = request.getHeader(AUTHORIZATION);
    if (header == null) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }
    if (!StringUtils.startsWithIgnoreCase(header, "Basic")) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }
    byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
    byte[] decoded;
    try {
      decoded = Base64.getDecoder().decode(base64Token);
      String token = new String(decoded, StandardCharsets.UTF_8);
      int delim = token.indexOf(":");
      if (delim == -1) {
        throw new BadCredentialsException("Invalid basic authentication token");
      }
      String email = token.substring(0, delim);
      if (email.toLowerCase().contains("test")) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }

    } catch (IllegalArgumentException e) {
      throw new BadCredentialsException("Failed to decode basic authentication token");
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
