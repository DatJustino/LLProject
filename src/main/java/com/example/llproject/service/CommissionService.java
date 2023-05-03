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
    return commissionRepository.save(commission);
  }

  public Optional<Commission> getCommissionById(Integer commissionId) {
    return commissionRepository.findById(commissionId);
  }

  public List<Commission> getAllCommissions() {
    return commissionRepository.findAll();
  }

  public void updateCommission(Commission commission) {
    commissionRepository.save(commission);
  }

  public void deleteCommission(Integer commissionId) {
    commissionRepository.deleteById(commissionId);
  }
}
