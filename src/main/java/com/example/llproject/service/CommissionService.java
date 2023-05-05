package com.example.llproject.service;

import com.example.llproject.model.Commission;
import com.example.llproject.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommissionService {

  private final CommissionRepository commissionRepository;

  @Autowired
  public CommissionService(CommissionRepository commissionRepository) {
    this.commissionRepository = commissionRepository;
  }

  public Commission createCommission(Commission commission) {
    if (commission == null) {
      throw new IllegalArgumentException("Commission object cannot be null");
    }
    return commissionRepository.save(commission);
  }

  public Optional<Commission> getCommissionById(Integer commissionId) {
    if (commissionId <= 0) {
      throw new IllegalArgumentException("Invalid commission ID: " + commissionId);
    }
    return commissionRepository.findById(commissionId);
  }

  public List<Commission> getAllCommissions() {
    return commissionRepository.findAll();
  }

  public void updateCommission(Commission commission) {
    Optional<Commission> existingCommission = commissionRepository.findById(commission.getOrderId());
    if (existingCommission.isPresent()) {
      commissionRepository.save(commission);
    } else {
      throw new IllegalArgumentException("Commission not found with ID: " + commission.getOrderId());
    }
  }

  public void deleteCommission(Integer commissionId) {
    Optional<Commission> existingCommission = commissionRepository.findById(commissionId);
    if (existingCommission.isPresent()) {
      commissionRepository.deleteById(commissionId);
    } else {
      throw new IllegalArgumentException("Commission not found with ID: " + commissionId);
    }
  }
}
