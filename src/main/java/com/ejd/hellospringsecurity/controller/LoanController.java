package com.ejd.hellospringsecurity.controller;

import com.ejd.hellospringsecurity.model.Loans;
import com.ejd.hellospringsecurity.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

  private final LoanRepository loanRepository;

  @GetMapping("/details")
  public List<Loans> getLoanDetails(@RequestParam int id) {
    return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
  }
}
