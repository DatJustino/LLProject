package com.example.llproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class IndexController {

  @GetMapping("/")
  public String index() {

    return "Index Page";
  }

  @GetMapping("/about")
  public String about() {
    return ("/about");
  }
  @GetMapping("/admin")
  public String admin() {
    return ("/admin");
  }

  @GetMapping("/register")
  public String register() {
    return ("/register");
  }
}
