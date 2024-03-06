package com.ejd.hellospringsecurity.controller;

import com.ejd.hellospringsecurity.model.User;
import com.ejd.hellospringsecurity.repository.UserRepository;
import com.ejd.hellospringsecurity.repository.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody User user) {
    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      User createdUser = userRepository.save(user);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body("User has been successfully created" + createdUser.getId());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Unable to register due to" + e.getMessage());
    }
  }
}
