package com.example.llproject.service;

import com.example.llproject.model.Admin;
import com.example.llproject.repository.AdminRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

  private final AdminRepository adminRepository;

  @Autowired
  public AdminService(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }

  public void createAdmin(Admin admin) {
    adminRepository.save(admin);
  }

  public Optional<Admin> getAdminById(Integer adminId) {
    return adminRepository.findById(adminId);
  }

  public List<Admin> getAllAdmins() {
    return adminRepository.findAll();
  }

  public void updateAdmin(Integer adminId, Admin updatedAdmin) {
    Optional<Admin> adminOptional = adminRepository.findById(adminId);
    if (adminOptional.isPresent()) {
      Admin admin = adminOptional.get();
      admin.setAdminEmail(updatedAdmin.getAdminEmail());
      admin.setAdminPassword(updatedAdmin.getAdminPassword());
      adminRepository.save(admin);
    } else {
      throw new IllegalArgumentException("Admin not found with ID: " + adminId);
    }
  }

  public void deleteAdmin(Integer adminId) {
    adminRepository.deleteById(adminId);
  }

/*  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Admin admin = adminRepository.findByAdminEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new User(admin.getAdminEmail(), admin.getAdminPassword(), new ArrayList<>());
  }*/
}
