package com.example.llproject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Commission entity for commissioning a painting.
 */
@Entity
@Table(name = "commission")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Commission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "commissionid")
  private Integer commissionId;
  @Column(name = "comfirstname")
  private String firstname;
  @Column(name = "comlastname")
  private String lastname;
  @NotNull
  @Column(name = "comemail")
  private String email;
  @Column(name = "comphonenumber")
  private String phonenumber;
  @NotNull
  private String subject;
  @NotNull
  private String description;
  @Column(name = "comformat1")
  private String pageformat1;
  @NotNull
  @Column(name = "comformat2")
  private String pageformat2;
  @Column(name = "comdeliverydate")
  private LocalDate deliverydate;
  private String street;
  @Column(name = "comhousenumber")
  private Integer housenumber;
  private String floor;
  @Column(name = "comzipcode")
  private Integer zipcode;
  @Column(name = "comimage1")
  private String imageurl1;
  @Column(name = "comimage2")
  private String imageurl2;
  @Column(name = "comimage3")
  private String imageurl3;
}
