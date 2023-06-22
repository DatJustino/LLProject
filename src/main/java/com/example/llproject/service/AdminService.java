package com.example.llproject.service;

import com.example.llproject.model.Admin;
import com.example.llproject.model.AdminDTO;
import com.example.llproject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class AdminService implements UserDetailsService {
  private final AdminRepository adminRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
    this.adminRepository = adminRepository;
    this.passwordEncoder = passwordEncoder;
  }
  public Optional<AdminDTO> findByAdminEmail(String email) {
    return adminRepository.findByAdminEmail(email).map(this::convertToDTO);
  }

  public void createAdmin(AdminDTO adminDTO, String password) {
    try {
      Admin admin = new Admin(adminDTO);
      String encodedPassword = passwordEncoder.encode(password);
      admin.setAdminPassword(encodedPassword);
      adminRepository.save(admin);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException("Admin email already in use");
    }
  }

    public Optional<AdminDTO> getAdminById(Integer adminId) {
      return adminRepository.findById(adminId)
          .map(this::convertToDTO);
    }

    public List<AdminDTO> getAllAdmins() {
      return adminRepository.findAll().stream()
          .map(this::convertToDTO)
          .collect(Collectors.toList());
    }

  public void updateAdmin(Integer adminId, AdminDTO updatedAdminDTO, String password) {
    Optional<Admin> adminOptional = adminRepository.findById(adminId);
    if (adminOptional.isPresent()) {
      Admin admin = adminOptional.get();
      admin.setAdminEmail(updatedAdminDTO.getAdminEmail());
      admin.setRole(updatedAdminDTO.getRole());
      String encodedPassword = passwordEncoder.encode(password);
      admin.setAdminPassword(encodedPassword);
      adminRepository.save(admin);
    } else {
      throw new IllegalArgumentException("Admin not found with ID: " + adminId);
    }
  }

    public void deleteAdmin(Integer adminId) {
      adminRepository.deleteById(adminId);
    }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Admin admin = adminRepository.findByAdminEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
    return User.withUsername(admin.getAdminEmail())
        .password(admin.getAdminPassword())
        .roles(admin.getRole())
        .build();
  }

  private AdminDTO convertToDTO(Admin admin) {
    return new AdminDTO(admin);
  }

  private Admin convertToEntity(AdminDTO adminDTO) {
    return new Admin(adminDTO);
  }
}