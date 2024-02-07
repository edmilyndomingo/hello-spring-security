package com.ejd.hellospringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {

  @GetMapping("/details")
  public String getLoanDetails() {
    return "Here are the loan details from the db";
  }
}
