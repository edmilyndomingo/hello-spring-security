package com.ejd.hellospringsecurity.repository;

import com.ejd.hellospringsecurity.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Deprecated
public class UserService implements UserDetailsService {
  private final CustomerRepository customerRepository;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     Customer user = customerRepository.findByEmail(email);
     if (user == null) {
       throw new UsernameNotFoundException("User details not found for the email: "+ email);
     }
    return org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(user.getPassword())
        .authorities(new SimpleGrantedAuthority(user.getRole()))
        .build();
  }

  public Customer createUser(Customer user) {
    return customerRepository.save(user);
  }
}
