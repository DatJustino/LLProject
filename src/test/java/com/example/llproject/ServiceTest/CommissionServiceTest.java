package com.example.llproject.ServiceTest;

import com.example.llproject.model.Commission;
import com.example.llproject.repository.CommissionRepository;
import com.example.llproject.service.CommissionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;
@DataJpaTest
public class CommissionServiceTest {

  @Mock
  private CommissionRepository commissionRepository;

  private CommissionService commissionService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    commissionService = new CommissionService(commissionRepository);
  }

  @Test
  public void testCreateCommission() {
    // Arrange
    Commission commission = new Commission();
    // Set necessary properties of the commission object

    when(commissionRepository.save(commission)).thenReturn(commission);

    // Act
    Commission createdCommission = commissionService.createCommission(commission);

    // Assert
    assertEquals(commission, createdCommission);
    verify(commissionRepository, times(1)).save(commission);
  }

  @Test
  public void testCreateCommission_NullCommission() {
    // Arrange
    Commission commission = null;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> commissionService.createCommission(commission));
  }

  @Test
  public void testGetCommissionById() {
    // Arrange
    Integer commissionId = 1;
    Commission commission = new Commission();
    // Set necessary properties of the commission object

    when(commissionRepository.findById(commissionId)).thenReturn(Optional.of(commission));

    // Act
    Optional<Commission> retrievedCommission = commissionService.getCommissionById(commissionId);

    // Assert
    assertEquals(Optional.of(commission), retrievedCommission);
    verify(commissionRepository, times(1)).findById(commissionId);
  }

  @Test
  public void testGetCommissionById_InvalidCommissionId() {
    // Arrange
    Integer commissionId = 0;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> commissionService.getCommissionById(commissionId));
  }

  @Test
  public void testGetAllCommissions() {
    // Arrange
    List<Commission> commissionList = new ArrayList<>();
    // Add some commissions to the list

    when(commissionRepository.findAll()).thenReturn(commissionList);

    // Act
    List<Commission> retrievedCommissions = commissionService.getAllCommissions();

    // Assert
    assertEquals(commissionList, retrievedCommissions);
    verify(commissionRepository, times(1)).findAll();
  }

  @Test
  public void testUpdateCommission() {
    // Arrange
    Integer commissionId = 1;
    Commission commission = new Commission();
    // Set necessary properties of the commission object

    when(commissionRepository.findById(commission.getCommissionId())).thenReturn(Optional.of(commission));
    when(commissionRepository.save(commission)).thenReturn(commission);

    // Act
    commissionService.updateCommission(commissionId, commission);

    // Assert
    verify(commissionRepository, times(1)).findById(commission.getCommissionId());
    verify(commissionRepository, times(1)).save(commission);
  }

  @Test
  public void testUpdateCommission_CommissionNotFound() {
    // Arrange
    Integer commissionId = 1;
    Commission commission = new Commission();
    // Set necessary properties of the commission object

    when(commissionRepository.findById(commission.getCommissionId())).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> commissionService.updateCommission(commissionId, commission));
  }

  @Test
  public void testDeleteCommission() {
    // Arrange
    Integer commissionId = 1;
    Commission commission = new Commission();
    // Set necessary properties of the commission object

    when(commissionRepository.findById(commissionId)).thenReturn(Optional.of(commission));

    // Act
    commissionService.deleteCommission(commissionId);

    // Assert
    verify(commissionRepository, times(1)).findById(commissionId);
    verify(commissionRepository, times(1)).deleteById(commissionId);
  }

  @Test
  public void testDeleteCommission_CommissionNotFound() {
    // Arrange
    Integer commissionId = 1;

    when(commissionRepository.findById(commissionId)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> commissionService.deleteCommission(commissionId));
  }
}
