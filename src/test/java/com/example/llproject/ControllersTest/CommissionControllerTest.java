package com.example.llproject.ControllersTest;

import com.example.llproject.controller.CommissionController;
import com.example.llproject.model.Commission;
import com.example.llproject.service.CommissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommissionControllerTest {

  @Mock
  private CommissionService commissionService;

  @InjectMocks
  private CommissionController commissionController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateCommission() {
    // Create a commission
    Commission commission = new Commission();
    commission.setFName("John");
    commission.setLName("Doe");
    // Set other properties

    // Mock the createCommission method of CommissionService
    when(commissionService.createCommission(commission)).thenReturn(commission);

    // Invoke the createCommission method of CommissionController
    ResponseEntity<Commission> response = commissionController.createCommission(commission);

    // Verify the response status code and body
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(commission, response.getBody());

    // Verify that the createCommission method of CommissionService is called
    verify(commissionService, times(1)).createCommission(commission);
  }

  @Test
  void testGetCommissionById_CommissionExists() {
    // Create a commission
    Commission commission = new Commission();
    commission.setCommissionId(1001);
    // Set other properties

    // Mock the getCommissionById method of CommissionService
    when(commissionService.getCommissionById(commission.getCommissionId())).thenReturn(Optional.of(commission));

    // Invoke the getCommissionById method of CommissionController
    ResponseEntity<Commission> response = commissionController.getCommissionById(commission.getCommissionId());

    // Verify the response status code and body
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(commission, response.getBody());

    // Verify that the getCommissionById method of CommissionService is called
    verify(commissionService, times(1)).getCommissionById(commission.getCommissionId());
  }

  @Test
  void testGetCommissionById_CommissionNotFound() {
    // Create a commission
    Integer commissionId = 1001;

    // Mock the getCommissionById method of CommissionService to return an empty Optional
    when(commissionService.getCommissionById(commissionId)).thenReturn(Optional.empty());

    // Invoke the getCommissionById method of CommissionController
    ResponseEntity<Commission> response = commissionController.getCommissionById(commissionId);

    // Verify the response status code
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    // Verify that the getCommissionById method of CommissionService is called
    verify(commissionService, times(1)).getCommissionById(commissionId);
  }
  @Test
  void testGetAllCommissions() {
    // Create a list of commissions
    List<Commission> commissions = new ArrayList<>();
    // Add commissions to the list

    // Mock the getAllCommissions method of CommissionService
    when(commissionService.getAllCommissions()).thenReturn(commissions);

    // Invoke the getAllCommissions method of CommissionController
    ResponseEntity<List<Commission>> response = commissionController.getAllCommissions();

    // Verify the response status code and body
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(commissions, response.getBody());
    // Verify that the getAllCommissions method of CommissionService is called
    verify(commissionService, times(1)).getAllCommissions();
  }
  @Test
  void testUpdateCommission_CommissionExists() {
    // Create a commission
    Commission commission = new Commission();
    commission.setCommissionId(1001);
    // Set other properties

    // Mock the getCommissionById method of CommissionService to return the commission
    when(commissionService.getCommissionById(commission.getCommissionId())).thenReturn(Optional.of(commission));

    // Invoke the updateCommission method of CommissionController
    ResponseEntity<String> response = commissionController.updateCommission(commission.getCommissionId(), commission);

    // Verify the response status code
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Commission updated successfully", response.getBody());

    // Verify that the getCommissionById method of CommissionService is called
    verify(commissionService, times(1)).getCommissionById(commission.getCommissionId());
    // Verify that the updateCommission method of CommissionService is called
    verify(commissionService, times(1)).updateCommission(existingCommission.getCommissionId(), commission);
  }

  @Test
  void testUpdateCommission_CommissionNotFound() {
    // Create a commission
    Integer commissionId = 1001;
    Commission updatedCommission = new Commission();
    // Set updated properties

    // Mock the getCommissionById method of CommissionService to return an empty Optional
    when(commissionService.getCommissionById(commissionId)).thenReturn(Optional.empty());

    // Invoke the updateCommission method of CommissionController
    ResponseEntity<String> response = commissionController.updateCommission(commissionId, updatedCommission);

    // Verify the response status code
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    // Verify that the getCommissionById method of CommissionService is called
    verify(commissionService, times(1)).getCommissionById(commissionId);
    // Verify that the updateCommission method of CommissionService is not called
    verify(commissionService, never()).updateCommission(existingCommission.getCommissionId(), updatedCommission);
  }

  @Test
  void testDeleteCommission_CommissionExists() {
    // Create a commission
    Commission commission = new Commission();
    commission.setCommissionId(1001);
    // Set other properties

    // Mock the getCommissionById method of CommissionService to return the commission
    when(commissionService.getCommissionById(commission.getCommissionId())).thenReturn(Optional.of(commission));

    // Invoke the deleteCommission method of CommissionController
    ResponseEntity<String> response = commissionController.deleteCommission(commission.getCommissionId());

    // Verify the response status code
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Commission deleted successfully", response.getBody());

    // Verify that the getCommissionById method of CommissionService is called
    verify(commissionService, times(1)).getCommissionById(commission.getCommissionId());
    // Verify that the deleteCommission method of CommissionService is called
    verify(commissionService, times(1)).deleteCommission(commission.getCommissionId());
  }
  @Test
  void testDeleteCommission_CommissionNotFound() {
    // Create a commission
    Integer commissionId = 1001;

    // Mock the getCommissionById method of CommissionService to return an empty Optional
    when(commissionService.getCommissionById(commissionId)).thenReturn(Optional.empty());

    // Invoke the deleteCommission method of CommissionController
    ResponseEntity<String> response = commissionController.deleteCommission(commissionId);

    // Verify the response status code
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    // Verify that the getCommissionById method of CommissionService is called
    verify(commissionService, times(1)).getCommissionById(commissionId);
    // Verify that the deleteCommission method of CommissionService is not called
    verify(commissionService, never()).deleteCommission(commissionId);
  }
}
