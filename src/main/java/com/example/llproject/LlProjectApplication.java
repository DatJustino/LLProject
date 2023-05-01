package com.example.llproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.llproject.controller", "com.example.llproject.service", "com.example.llproject.repository"})
public class LlProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(LlProjectApplication.class, args);
  }

}
