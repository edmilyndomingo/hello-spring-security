package com.ejd.hellospringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class BalanceController {

  @GetMapping("/details")
  public String getBalance() {
    return "Return balance details from the db";
  }
}
