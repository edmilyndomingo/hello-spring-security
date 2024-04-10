package com.ejd.hellospringsecurity.repository;

import com.ejd.hellospringsecurity.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {


}
