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
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    
    
    
}
