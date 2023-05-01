package com.example.llproject.model;


import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "orderid")
  private Integer orderId;
  private String phoneNumber;
  @Column(name = "fname")
  private String fName;
  @Column(name = "lname")
  private String lName;
  private String subject;
  private String description;
  private String address;
  @Lob
  @Column(columnDefinition = "BLOB")
  private byte[] image;


  @ManyToMany
  @JoinTable(
      name = "order_product",
      joinColumns = @JoinColumn(name = "orderid"),
      inverseJoinColumns = @JoinColumn(name = "productid")
  )
  private List<Product> productsList;

}