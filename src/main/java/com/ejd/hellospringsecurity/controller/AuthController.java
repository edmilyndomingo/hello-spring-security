package com.ejd.hellospringsecurity.controller;

import com.ejd.hellospringsecurity.model.Customer;
import com.ejd.hellospringsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody Customer user) {
    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setCreatedAt(LocalDate.now().toString());
      Customer createdUser = customerRepository.save(user);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body("User has been successfully created" + createdUser.getId());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Unable to register due to" + e.getMessage());
    }
  }

  @GetMapping("/user")
  public Customer getUserDetailsAfterLogin(Authentication authentication) {
    return customerRepository.findByEmail(authentication.getName());
  }
}
