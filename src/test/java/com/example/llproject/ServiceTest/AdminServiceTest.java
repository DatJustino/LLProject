package com.example.llproject.ServiceTest;

import com.example.llproject.model.Admin;
import com.example.llproject.repository.AdminRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

  @Mock
  private AdminRepository adminRepository;

  @InjectMocks
  private com.example.llproject.service.AdminService adminService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateAdmin() {
    Admin admin = new Admin();
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    adminService.createAdmin(admin);

    verify(adminRepository, times(1)).save(admin);
  }

  @Test
  public void testGetAdminById_existingAdmin() {
    Admin admin = new Admin();
    admin.setAdminId(1);
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    when(adminRepository.findById(1)).thenReturn(Optional.of(admin));

    Optional<Admin> retrievedAdmin = adminService.getAdminById(1);

    Assertions.assertTrue(retrievedAdmin.isPresent());
    Assertions.assertEquals(admin.getAdminId(), retrievedAdmin.get().getAdminId());
    Assertions.assertEquals(admin.getAdminEmail(), retrievedAdmin.get().getAdminEmail());
    Assertions.assertEquals(admin.getAdminPassword(), retrievedAdmin.get().getAdminPassword());

    verify(adminRepository, times(1)).findById(1);
  }

  @Test
  public void testGetAdminById_nonExistingAdmin() {
    when(adminRepository.findById(1)).thenReturn(Optional.empty());

    Optional<Admin> retrievedAdmin = adminService.getAdminById(1);

    Assertions.assertFalse(retrievedAdmin.isPresent());

    verify(adminRepository, times(1)).findById(1);
  }

  @Test
  public void testUpdateAdmin_existingAdmin() {
    Admin admin = new Admin();
    admin.setAdminId(1);
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    Admin updatedAdmin = new Admin();
    updatedAdmin.setAdminEmail("updatedadmin@example.com");
    updatedAdmin.setAdminPassword("updatedpassword");

    when(adminRepository.findById(1)).thenReturn(Optional.of(admin));

    adminService.updateAdmin(1, updatedAdmin);

    verify(adminRepository, times(1)).save(admin);
    Assertions.assertEquals(updatedAdmin.getAdminEmail(), admin.getAdminEmail());
    Assertions.assertEquals(updatedAdmin.getAdminPassword(), admin.getAdminPassword());
  }

  @Test
  public void testUpdateAdmin_nonExistingAdmin() {
    when(adminRepository.findById(1)).thenReturn(Optional.empty());

    Admin updatedAdmin = new Admin();
    updatedAdmin.setAdminEmail("updatedadmin@example.com");
    updatedAdmin.setAdminPassword("updatedpassword");

    Assertions.assertThrows(IllegalArgumentException.class, () -> adminService.updateAdmin(1, updatedAdmin));

    verify(adminRepository, never()).save(any(Admin.class));
  }
  @Test
  public void testDeleteAdmin() {
    Admin admin = new Admin();
    admin.setAdminId(1);
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    when(adminRepository.findById(1)).thenReturn(Optional.of(admin));

    adminService.deleteAdmin(1);

    verify(adminRepository, times(1)).deleteById(1);
  }
}