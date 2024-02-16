package com.ejd.hellospringsecurity.repository;

import com.ejd.hellospringsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     User user = userRepository.findByEmail(email);
     if (user == null) {
       throw new UsernameNotFoundException("User details not found for the email: "+ email);
     }
    return org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(user.getPassword())
        .authorities(new SimpleGrantedAuthority(user.getRole()))
        .build();
  }
}
