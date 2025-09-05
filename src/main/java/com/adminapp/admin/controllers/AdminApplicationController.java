package com.adminapp.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adminapp.admin.models.ServiceProviders;
import com.adminapp.admin.services.AdminApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/service-providers/applications")
@PreAuthorize("hasRole('ADMIN')")
public class AdminApplicationController {
    
    @Autowired
    private  AdminApplicationService applicationService;

    @GetMapping("/all-applications")
    public ResponseEntity<List<ServiceProviders>> getAllApplications() {
        List <ServiceProviders> all = applicationService.getAllProviders();
        return ResponseEntity.ok(all);
    }

    @PutMapping("/approve/{userId}")
    public ResponseEntity<?> ApproveApplicants(@PathVariable Long userId) {
        applicationService.approve(userId);
        return ResponseEntity.ok("user approved");
    }
    
}
