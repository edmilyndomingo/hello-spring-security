package com.ejd.hellospringsecurity.controller;

import com.ejd.hellospringsecurity.model.User;
import com.ejd.hellospringsecurity.repository.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody User user) {
    try {
      User createdUser = userService.createUser(user);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body("User has been successfully created" + createdUser.getId());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Unable to register due to" + e.getMessage());
    }
  }
}
