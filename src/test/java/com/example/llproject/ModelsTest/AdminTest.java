package com.example.llproject.ModelsTest;

import com.example.llproject.model.Admin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AdminTest {

  @Test
  public void testAdminModel() {
    // Create an instance of the Admin class
    Admin admin = new Admin();
    admin.setAdminId(1);
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    // Verify the values
    Assertions.assertEquals(1, admin.getAdminId());
    Assertions.assertEquals("admin@example.com", admin.getAdminEmail());
    Assertions.assertEquals("password", admin.getAdminPassword());
  }
}