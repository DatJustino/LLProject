package com.example.llproject.model;


import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Order entity for order information (as in shopping cart).
 */
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
  @Column(name = "orderfname")
  private String fName;
  @Column(name = "orderlname")
  private String lName;
  @Column(name = "orderemail")
  private String email;
  @Column(name = "orderphonenumber")
  private String phoneNumber;
  private String address;
  @Column(name = "orderhousenumber")
  private Integer houseNumber;
  private String floor;
  @Column(name = "orderzipcode")
  private Integer zipCode;



  @ManyToMany
  @JoinTable(
      name = "orderproducts",
      joinColumns = @JoinColumn(name = "orderid"),
      inverseJoinColumns = @JoinColumn(name = "productid")
  )
  private List<Product> productsList;

}