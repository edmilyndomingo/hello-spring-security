package com.ejd.hellospringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
      http.csrf()
          .disable()
        .authorizeHttpRequests((requests) -> requests
        .requestMatchers("/account/details","/balance/details","/loan/details","/card/details").authenticated()
        .requestMatchers("/notice","/contact","/auth/register").permitAll())
      .formLogin(Customizer.withDefaults())
      .httpBasic(Customizer.withDefaults());
      return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
