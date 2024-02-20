package com.ejd.hellospringsecurity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  // native = it will rely on how the db will generate the ID
  @GenericGenerator(name = "native")
  private Long id;
  @NotBlank
  private String username;
  @NotBlank
  private String email;
  @NotBlank
  private String password;
  private boolean enabled;
  private String role;
}
