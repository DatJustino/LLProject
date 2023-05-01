/*
package com.example.llproject.security;

import com.example.llproject.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private CustomerRepo customerRepo;

  public CustomUserDetailsService(CustomerRepo customerRepo) {
    this.customerRepo = customerRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = customerRepo.findBycEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    return UserPrincipal.create(user);
  }
}
*/
