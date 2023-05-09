package com.example.llproject.ServiceTest;

import com.example.llproject.model.Commission;
import com.example.llproject.repository.CommissionRepository;
import com.example.llproject.service.CommissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommissionServiceTest {

  private CommissionService commissionService;
  private CommissionRepository commissionRepository;

  @BeforeEach
  void setUp() {
    commissionRepository = mock(CommissionRepository.class);
    commissionService = new CommissionService(commissionRepository);
  }

  @Test
  void testCreateCommission() {
    // Create a Commission object
    Commission commission = new Commission();
    commission.setCommissionId(100);

    // Mock the save method of CommissionRepository
    when(commissionRepository.save(commission)).thenReturn(commission);

    // Call the createCommission method
    Commission createdCommission = commissionService.createCommission(commission);
    // Verify that the save method of CommissionRepository is called
    verify(commissionRepository, times(1)).save(commission);

    // Verify that the returned Commission object is the same as the created one
    assertEquals(commission, createdCommission);
  }

  @Test
  void testGetCommissionById_CommissionFound() {
    // Create a Commission object
    Commission commission = new Commission();
    commission.setCommissionId(100);

    // Mock the findById method of CommissionRepository to return the Commission object
    when(commissionRepository.findById(100)).thenReturn(Optional.of(commission));

    // Call the getCommissionById method
    Optional<Commission> foundCommission = commissionService.getCommissionById(100);

    // Verify that the findById method of CommissionRepository is called
    verify(commissionRepository, times(1)).findById(100);

    // Verify that the returned Optional contains the Commission object
    assertTrue(foundCommission.isPresent());
    assertEquals(commission, foundCommission.get());
  }

  @Test
  void testGetCommissionById_CommissionNotFound() {
    // Mock the findById method of CommissionRepository to return an empty Optional
    when(commissionRepository.findById(100)).thenReturn(Optional.empty());

    // Call the getCommissionById method
    Optional<Commission> foundCommission = commissionService.getCommissionById(100);

    // Verify that the findById method of CommissionRepository is called
    verify(commissionRepository, times(1)).findById(100);

    // Verify that the returned Optional is empty
    assertFalse(foundCommission.isPresent());
  }

  @Test
  void testGetAllCommissions() {
    // Create a list of Commission objects
    List<Commission> commissions = new ArrayList<>();
    commissions.add(new Commission(100, "John", "Doe", "john@example.com",
        "1234567890", "Subject", "Description", "Address", 1, "Floor", 12345, new byte[0]));
    commissions.add(new Commission(200, "Jane", "Doe", "jane@example.com",
        "9876543210", "Subject", "Description", "Address", 2, "Floor", 54321, new byte[0]));

    // Mock the findAll method of CommissionRepository to return the list of Commission objects
    when(commissionRepository.findAll()).thenReturn(commissions);

    // Call the getAllCommissions method
    List<Commission> retrievedCommissions = commissionService.getAllCommissions();

    // Verify that the findAll method of CommissionRepository is called
    verify(commissionRepository, times(1)).findAll();

    // Verify that the returned list contains all the Commission objects
    assertEquals(commissions.size(), retrievedCommissions.size());
    assertEquals(commissions, retrievedCommissions);
  }

  @Test
  void testUpdateCommission() {
    // Create a Commission object
    Commission commission = new Commission();
    commission.setCommissionId(100);

    // Create a mock CommissionRepository object
    CommissionRepository commissionRepository = mock(CommissionRepository.class);

    // Create a mock CommissionService object
    CommissionService commissionService = new CommissionService(commissionRepository);

    // Mock the findById method of CommissionRepository to return the Commission object
    when(commissionRepository.findById(commission.getCommissionId())).thenReturn(Optional.of(commission));

    // Mock the save method of CommissionRepository
    when(commissionRepository.save(commission)).thenReturn(commission);

    // Call the updateCommission method
    commissionService.updateCommission(commission.getCommissionId(), commission);

    // Verify that the findById method of CommissionRepository is called
    verify(commissionRepository, times(1)).findById(commission.getCommissionId());

    // Verify that the save method of CommissionRepository is called
    verify(commissionRepository, times(1)).save(commission);
  }

  @Test
  void testDeleteCommission() {
    // Create a Commission object
    Commission commission = new Commission();
    commission.setCommissionId(100);

    // Mock the findById method of CommissionRepository to return the Commission object
    when(commissionRepository.findById(100)).thenReturn(Optional.of(commission));

    // Call the deleteCommission method
    commissionService.deleteCommission(100);

    // Verify that the findById method of CommissionRepository is called
    verify(commissionRepository, times(1)).findById(100);

    // Verify that the deleteById method of CommissionRepository is called
    verify(commissionRepository, times(1)).deleteById(100);
  }
  @Test
  void testCreateCommission_NullCommission() {
    // Call the createCommission method with a null Commission object
    assertThrows(IllegalArgumentException.class,
        () -> commissionService.createCommission(null));

    // Verify that the save method of CommissionRepository is not called
    verify(commissionRepository, never()).save(any(Commission.class));
  }

  @Test
  void testGetCommissionById_InvalidId() {
    // Call the getCommissionById method with an invalid Commission ID
    assertThrows(IllegalArgumentException.class,
        () -> commissionService.getCommissionById(-1));

    // Verify that the findById method of CommissionRepository is not called
    verify(commissionRepository, never()).findById(anyInt());
  }
  @Test
  void testUpdateCommission_CommissionNotFound() {
    // Create a Commission object
    Commission commission = new Commission();
    commission.setCommissionId(100);

    // Create a mock CommissionRepository object
    CommissionRepository commissionRepository = mock(CommissionRepository.class);

    // Create a mock CommissionService object
    CommissionService commissionService = new CommissionService(commissionRepository);

    // Mock the findById method of CommissionRepository to return an empty Optional
    when(commissionRepository.findById(commission.getCommissionId())).thenReturn(Optional.empty());

    // Call the updateCommission method and expect an exception
    assertThrows(IllegalArgumentException.class,
        () -> commissionService.updateCommission(commission.getCommissionId(), commission));

    // Verify that the findById method of CommissionRepository is called
    verify(commissionRepository, times(1)).findById(commission.getCommissionId());

    // Verify that the save method of CommissionRepository is not called
    verify(commissionRepository, never()).save(any(Commission.class));
  }

  @Test
  void testDeleteCommission_CommissionNotFound() {
    // Mock the findById method of CommissionRepository to return an empty Optional
    when(commissionRepository.findById(100)).thenReturn(Optional.empty());

    // Call the deleteCommission method and expect an exception
    assertThrows(IllegalArgumentException.class,
        () -> commissionService.deleteCommission(100));

    // Verify that the deleteById method of CommissionRepository is not called
    verify(commissionRepository, never()).deleteById(anyInt());
  }
}