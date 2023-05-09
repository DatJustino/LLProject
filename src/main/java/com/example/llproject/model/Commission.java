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
  @Column(name = "comfname")
  private String fName;
  @Column(name = "comlname")
  private String lName;
  @NotNull
  @Column(name = "comemail")
  private String email;
  @Column(name = "comphonenumber")
  private String phoneNumber;
  @NotNull
  private String subject;
  @NotNull
  private String description;
  @Column(name = "comformat1")
  private String pageFormat1;
  @NotNull
  @Column(name = "comformat2")
  private String pageFormat2;
  @NotNull
  @Column(name = "comdeliverydate")
  private LocalDate deliveryDate;
  private String street;
  @Column(name = "comhousenumber")
  private Integer houseNumber;
  private String floor;
  @Column(name = "comzipcode")
  private Integer zipCode;


  @NotNull
  @Lob
  @Column(name = "image1", columnDefinition = "BLOB")
  private byte[] image1;

  @Lob
  @Column(name = "image2", columnDefinition = "BLOB")
  private byte[] image2;

  @Lob
  @Column(name = "image3", columnDefinition = "BLOB")
  private byte[] image3;

}
