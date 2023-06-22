package com.example.llproject.repository;

import com.example.llproject.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
  Optional<Admin> findByAdminEmail(String email);

}
