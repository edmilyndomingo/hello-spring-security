package com.ejd.hellospringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardsController {
  @GetMapping("/details")
  public String getCardDetails() {
    return "Here are the card details from the DB";
  }
}
