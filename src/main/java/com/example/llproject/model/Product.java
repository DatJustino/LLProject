package com.example.llproject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @Column(name = "productid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Integer productId;
  private String productName;
  private String productDescription;
  private String productImage;
  private Integer productPrice;
  private Integer productQuantity;
  private String width;
  private String height;
  private String length;

  @ManyToMany(mappedBy = "productsList")
  @JsonBackReference
  private List<Order> orders;

  }

