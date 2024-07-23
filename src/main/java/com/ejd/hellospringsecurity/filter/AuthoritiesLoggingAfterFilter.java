package com.ejd.hellospringsecurity.filter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || StringUtils.isBlank(authentication.getName())) {
      filterChain.doFilter(servletRequest, servletResponse);
    }
    log.info("User <{}> is successfully authenticated and has the authorities <{}>",
        authentication.getName(), authentication.getAuthorities().toString());
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
