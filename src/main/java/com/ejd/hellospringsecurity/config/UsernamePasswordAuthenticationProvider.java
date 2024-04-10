package com.ejd.hellospringsecurity.config;

import com.ejd.hellospringsecurity.model.Customer;
import com.ejd.hellospringsecurity.model.User;
import com.ejd.hellospringsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    final String email = authentication.getName();
    final String password = authentication.getCredentials().toString();
    final Customer user = customerRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User details not found for the email: "+ email);
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Invalid Password");
    }

    return new UsernamePasswordAuthenticationToken(email, password, List.of(new SimpleGrantedAuthority(user.getRole())));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
