package com.example.llproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  @Id
  @Column(length = 8)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer customerId;


  @NotEmpty
  @Email
  @Column(name = "cemail")
  private String cEmail;

  @NotEmpty
  @Size(min = 6, max = 20)
  @Column(name = "cpassword")
  private String cPassword;

}
