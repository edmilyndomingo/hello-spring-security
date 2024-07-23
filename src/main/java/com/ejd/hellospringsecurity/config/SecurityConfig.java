package com.ejd.hellospringsecurity.config;

import com.ejd.hellospringsecurity.filter.AuthoritiesLoggingAfterFilter;
import com.ejd.hellospringsecurity.filter.CsrfCookieFilter;
import com.ejd.hellospringsecurity.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
      http
          // securityContext().requireExplicitSave(false) - we are telling the spring security framework
          // im not going to take the responsibility of saving the authentication details
          // inside the securitycontextframework and letting the framework to do all the magic for me
          // by setting this to fault, we're giving this responsibility of generating the JSESSIONID
          // and storing of auth details to the framework
          // and sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
          // allows accessing protected url without asking for your credentials everytime
          .securityContext().requireExplicitSave(false)
          .and().sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
        .cors().configurationSource(corsConfigurationSource)
        .and()
        .csrf((configurer) ->
          configurer.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
            .ignoringRequestMatchers("/auth/register")
            // withHttpOnlyFalse allows javascript code to read the cookie value
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new AuthoritiesLoggingAfterFilter(), CsrfCookieFilter.class)
        .authorizeHttpRequests((requests) -> requests
          .requestMatchers("/auth/user").authenticated()
          .requestMatchers("/account/details").hasRole("USER")
          .requestMatchers("/balance/details").hasAnyRole("USER", "ADMIN")
          .requestMatchers("/loan/details").hasRole("USER")
          .requestMatchers("/card/details").hasRole("USER")
          .requestMatchers("/notice","/auth/register", "/contact").permitAll())
      .formLogin(Customizer.withDefaults())
      .httpBasic(Customizer.withDefaults());
      return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    return request -> {
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowedOrigins(Collections.singletonList("*"));
      config.setAllowedMethods(Collections.singletonList("*"));
      config.setAllowCredentials(true);
      config.setAllowedHeaders(Collections.singletonList("*"));
      config.setMaxAge(3600L);
      return config;
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
