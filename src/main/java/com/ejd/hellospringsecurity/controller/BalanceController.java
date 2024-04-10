package com.ejd.hellospringsecurity.controller;

import com.ejd.hellospringsecurity.model.AccountTransactions;
import com.ejd.hellospringsecurity.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

  private final AccountTransactionsRepository accountTransactionsRepository;

  @GetMapping("/details")
  public List<AccountTransactions> getBalance(@RequestParam int id) {
    return accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);
  }
}
