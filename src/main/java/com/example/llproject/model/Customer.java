package com.example.llproject.model;


import jakarta.persistence.*;

@Entity
public class Customer {

  @Id
 // @Column(length = 8)
  private Integer customerId;
  @Column(name="cemail")
  private String cEmail;
  @Column(name="cpassword")
  private String cPassword;


  public String getcEmail() {
    return cEmail;
  }

  public void setcEmail(String cEmail) {
    this.cEmail = cEmail;
  }

  public String getcPassword() {
    return cPassword;
  }

  public void setcPassword(String cPassword) {
    this.cPassword = cPassword;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

}
