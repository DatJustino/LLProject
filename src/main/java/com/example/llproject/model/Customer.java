package com.example.llproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
/**
 * Customer entity for customer login information and nyhedsbrev.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  @Id
  @Column(name = "customerid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer customerId;
  @NotEmpty
  @Email
  @Column(name = "cemail", columnDefinition = "varchar(50)")
  private String cEmail;
  @NotEmpty
  @Size(min = 6, max = 20)
  @Column(name = "cpassword", columnDefinition = "varchar(50)")
  private String cPassword;
  @Column(name = "newsletter", columnDefinition = "boolean default false")
  private boolean newsLetter;

}
