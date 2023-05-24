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
  @Column(name = "comfirstname", columnDefinition = "varchar(20)")
  private String firstname;
  @Column(name = "comlastname", columnDefinition = "varchar(20)")
  private String lastname;
  @NotNull
  @Column(name = "comemail", columnDefinition = "varchar(50)")
  private String email;
  @Column(name = "comphonenumber" , columnDefinition = "varchar(15)")
  private String phonenumber;
  @NotNull
  @Column(name = "comsubject", columnDefinition = "varchar(100)")
  private String subject;
  @NotNull
  @Column(name = "description", columnDefinition = "varchar(1000)")
  private String description;
  @Column(name = "comformat1", columnDefinition = "varchar(20)")
  private String pageformat1;
  @NotNull
  @Column(name = "comformat2", columnDefinition = "varchar(20)")
  private String pageformat2;
  @Column(name = "comdeliverydate")
  private LocalDate deliverydate;
  private String street;
  @Column(name = "comhousenumber", columnDefinition = "varchar(10)")
  private Integer housenumber;
  private String floor;
  @Column(name = "comzipcode", columnDefinition = "varchar(10)")
  private Integer zipcode;
  @Column(name = "comimage1")
  private String imageurl1;
  @Column(name = "comimage2")
  private String imageurl2;
  @Column(name = "comimage3")
  private String imageurl3;
}
