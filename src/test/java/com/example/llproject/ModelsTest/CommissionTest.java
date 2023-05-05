package com.example.llproject.ModelsTest;

import com.example.llproject.model.Commission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommissionTest {

  @Test
  void testCommissionConstructor() {
    // Create a Commission object using the constructor
    Commission commission = new Commission(100, "John", "Doe", "john@example.com",
        "1234567890", "Subject", "Description", "Address", 1, "Floor", 12345, new byte[0]);

    // Verify the values are set correctly
    assertEquals(100, commission.getCommissionId());
    assertEquals("John", commission.getFName());
    assertEquals("Doe", commission.getLName());
    assertEquals("john@example.com", commission.getEmail());
    assertEquals("1234567890", commission.getPhoneNumber());
    assertEquals("Subject", commission.getSubject());
    assertEquals("Description", commission.getDescription());
    assertEquals("Address", commission.getAddress());
    assertEquals(1, commission.getHouseNumber());
    assertEquals("Floor", commission.getFloor());
    assertEquals(12345, commission.getZipCode());
    assertArrayEquals(new byte[0], commission.getImage());
  }

  @Test
  void testCommissionSetters() {
    // Create a Commission object
    Commission commission = new Commission();

    // Set values using the setters
    commission.setCommissionId(200);
    commission.setFName("Jane");
    commission.setLName("Doe");
    commission.setEmail("jane@example.com");
    commission.setPhoneNumber("9876543210");
    commission.setSubject("New Subject");
    commission.setDescription("New Description");
    commission.setAddress("New Address");
    commission.setHouseNumber(2);
    commission.setFloor("New Floor");
    commission.setZipCode(54321);
    commission.setImage(new byte[0]);

    // Verify the values are set correctly
    assertEquals(200, commission.getCommissionId());
    assertEquals("Jane", commission.getFName());
    assertEquals("Doe", commission.getLName());
    assertEquals("jane@example.com", commission.getEmail());
    assertEquals("9876543210", commission.getPhoneNumber());
    assertEquals("New Subject", commission.getSubject());
    assertEquals("New Description", commission.getDescription());
    assertEquals("New Address", commission.getAddress());
    assertEquals(2, commission.getHouseNumber());
    assertEquals("New Floor", commission.getFloor());
    assertEquals(54321, commission.getZipCode());
    assertArrayEquals(new byte[0], commission.getImage());
  }
}
