package com.ejd.hellospringsecurity.controller;

import com.ejd.hellospringsecurity.model.Accounts;
import com.ejd.hellospringsecurity.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountsRepository accountsRepository;


  @GetMapping("/details")
  public Accounts getAccountDetails(@RequestParam int id) {
    return accountsRepository.findByCustomerId(id);
  }
}
