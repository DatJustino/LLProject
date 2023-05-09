package com.example.llproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:63342"})
@ComponentScan(basePackages = {"com.example.llproject.controller", "com.example.llproject.service", "com.example.llproject.repository","com.example.llproject.model", "com.example.llproject.config"})
public class LlProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(LlProjectApplication.class, args);
  }
  }
