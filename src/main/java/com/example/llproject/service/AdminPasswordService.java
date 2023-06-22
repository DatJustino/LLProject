package com.example.llproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminPasswordService {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AdminPasswordService(@Lazy PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public String encode(String password) {
    return passwordEncoder.encode(password);
  }
}
