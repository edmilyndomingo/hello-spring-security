package com.ejd.hellospringsecurity.controller;

import com.ejd.hellospringsecurity.model.Cards;
import com.ejd.hellospringsecurity.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardsController {
  private final CardsRepository cardsRepository;
  @GetMapping("/details")
  public List<Cards> getCardDetails(@RequestParam int id) {
    return cardsRepository.findByCustomerId(id);
  }
}
