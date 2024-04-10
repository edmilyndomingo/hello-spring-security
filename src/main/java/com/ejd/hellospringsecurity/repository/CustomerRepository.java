package com.ejd.hellospringsecurity.repository;

import com.ejd.hellospringsecurity.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
  Customer findByEmail(String email);
}
