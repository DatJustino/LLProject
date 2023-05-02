package com.example.llproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Admin entity for adminstration of the website.
 */

@Entity
@Table(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {
  @Id
  @Column(name = "adminid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer adminId;

  @NotEmpty
  @Email
  @Column(name = "aemail")
  private String adminEmail;

  @NotEmpty
  @Size(min = 6, max = 20)
  @Column(name = "apassword")
  private String adminPassword;


}
