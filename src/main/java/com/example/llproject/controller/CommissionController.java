package com.example.llproject.controller;

import com.example.llproject.model.Commission;
import com.example.llproject.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RestControllerAdvice
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "Authorization"})
@RequestMapping("/commissions")
public class CommissionController {

  private final CommissionService commissionService;

  @Autowired
  public CommissionController(CommissionService commissionService) {
    this.commissionService = commissionService;
  }

  @GetMapping
  public ResponseEntity<List<Commission>> getAllCommissions() {
    List<Commission> commissions = commissionService.getAllCommissions();
    return ResponseEntity.ok(commissions);
  }
  @GetMapping("/{commissionId}")
  public ResponseEntity<Commission> getCommissionById(@PathVariable("commissionId") Integer commissionId) {
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    return commission.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }



  @PostMapping()
  public ResponseEntity<Commission> createCommission(
      @RequestParam("firstname") String firstname,
      @RequestParam("lastname") String lastname,
      @RequestParam("email") String email,
      @RequestParam("phonenumber") String phonenumber,
      @RequestParam("subject") String subject,
      @RequestParam("description") String description,
      @RequestParam("pageformat1") String pageformat1,
      @RequestParam("pageformat2") String pageformat2,
      @RequestParam("deliverydate") LocalDate deliverydate,
      @RequestParam("street") String street,
      @RequestParam("housenumber") Integer housenumber,
      @RequestParam("floor") String floor,
      @RequestParam("zipcode") Integer zipcode,
      @RequestParam("imageurl1") String imageurl1,
      @RequestParam("imageurl2") String imageurl2,
      @RequestParam("imageurl3") String imageurl3) {
    Commission commission = new Commission();
    commission.setFirstname(firstname);
    commission.setLastname(lastname);
    commission.setEmail(email);
    commission.setPhonenumber(phonenumber);
    commission.setSubject(subject);
    commission.setDescription(description);
    commission.setPageformat1(pageformat1);
    commission.setPageformat2(pageformat2);
    commission.setDeliverydate(deliverydate);
    commission.setStreet(street);
    commission.setHousenumber(housenumber);
    commission.setFloor(floor);
    commission.setZipcode(zipcode);
    commission.setImageurl1(imageurl1);
    commission.setImageurl2(imageurl2);
    commission.setImageurl3(imageurl3);

    Commission createdCommission = commissionService.createCommission(commission);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCommission);
  }


  @PutMapping("/{commissionId}")
  public ResponseEntity<String> updateCommission(@PathVariable("commissionId") Integer commissionId, @RequestBody Commission updatedCommission) {
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    if (commission.isPresent()) {
      updatedCommission.setCommissionId(commissionId);
      commissionService.updateCommission(commissionId, updatedCommission);
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
