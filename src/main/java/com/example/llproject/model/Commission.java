package com.example.llproject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  @Column(name = "comemail")
  private String email;
  @Column(name = "comphonenumber")
  private String phoneNumber;
  private String subject;
  private String description;
  private String address;
  @Column(name = "comhousenumber")
  private Integer houseNumber;
  private String floor;
  @Column(name = "comzipcode")
  private Integer zipCode;

  @Lob
  @Column(columnDefinition = "BLOB")
  private byte[] image;


}
