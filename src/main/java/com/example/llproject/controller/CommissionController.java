package com.example.llproject.controller;

import com.example.llproject.model.Commission;
import com.example.llproject.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RestControllerAdvice
@CrossOrigin
@RequestMapping("/commissions")
public class CommissionController {

  private final CommissionService commissionService;

  @Autowired
  public CommissionController(CommissionService commissionService) {
    this.commissionService = commissionService;
  }

  @PostMapping
  public ResponseEntity<Commission> createCommission(@RequestBody Commission commission) {
    Commission createdCommission = commissionService.createCommission(commission);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCommission);
  }

  @GetMapping("/{commissionId}")
  public ResponseEntity<Commission> getCommissionById(@PathVariable("commissionId") Integer commissionId) {
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    return commission.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Commission>> getAllCommissions() {
    List<Commission> commissions = commissionService.getAllCommissions();
    return ResponseEntity.ok(commissions);
  }

  @PutMapping("/{commissionId}")
  public ResponseEntity<String> updateCommission(@PathVariable("commissionId") Integer commissionId, @RequestBody Commission updatedCommission) {
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    if (commission.isPresent()) {
      updatedCommission.setCommissionId(commissionId);
      commissionService.updateCommission(existingCommission.getCommissionId(), updatedCommission);
      return ResponseEntity.ok("Commission updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{commissionId}")
  public ResponseEntity<String> deleteCommission(@PathVariable("commissionId") Integer commissionId) {
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    if (commission.isPresent()) {
      commissionService.deleteCommission(commissionId);
      return ResponseEntity.ok("Commission deleted successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
