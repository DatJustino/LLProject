package com.example.llproject.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO {
  private Integer id;
  private String adminEmail;
  private String role;

  public AdminDTO(Admin admin) {
    this.id = admin.getAdminId();
    this.adminEmail = admin.getAdminEmail();
    this.role = admin.getRole();
  }
}