package com.adminapp.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adminapp.admin.models.LoanApplications;
import com.adminapp.admin.services.AdminLoanAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private AdminLoanAppService loanAppService;

    @GetMapping("/all-loans")
    public ResponseEntity<List<LoanApplications>> getMethodName() {
        return ResponseEntity.ok(loanAppService.getAllLoans());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<LoanApplications>> getPendingLoans() {
        return ResponseEntity.ok(loanAppService.getListPendingLoans());
    }

    @GetMapping("/approved")
    public ResponseEntity<List<LoanApplications>> getApprovedLoans() {
        return ResponseEntity.ok(loanAppService.getListApprovedLoans());
    }

    @GetMapping("/rejected")
    public ResponseEntity<List<LoanApplications>> getRejectetLoan(@RequestParam String param) {
        return ResponseEntity.ok(loanAppService.getListApprovedLoans());
    }

    @PutMapping("approve/{loanid}")
    public ResponseEntity<?> approveLoan(@PathVariable Long loanid) {
       
        loanAppService.approve(loanid);
        return ResponseEntity.ok("approved");
    }


    @PutMapping("reject/{loanid}")
    public ResponseEntity<?> rejectLoan(@PathVariable Long loanid) {
       
        loanAppService.reject(loanid);
        return ResponseEntity.ok("approved");
    }
     
}
