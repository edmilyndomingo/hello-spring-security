package com.ejd.hellospringsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
  @GenericGenerator(name = "native")
  private int id;

  private String name;

  private String email;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  private String role;

  @Column(name = "create_at")
  private String createdAt;

  @JsonIgnore
  @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
  private Set<Authority> authorities;
}
