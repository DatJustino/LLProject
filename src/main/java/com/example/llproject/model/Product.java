package com.example.llproject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;

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
  @Column(name = "productname")
  private String productName;
  @Column(name = "productdescription")
  private String productDescription;
  @Column(name = "productimage")
  private String productImage;
  @Column(name = "productprice", columnDefinition = "Decimal(10,2)")
  private Double productPrice;
  @Column(name = "productquantity")
  private Integer productQuantity;
  private Double width;
  private Double height;
  private Double length;

  @ManyToMany(mappedBy = "productsList")
  @JsonBackReference
  private List<Order> orders;

  }

