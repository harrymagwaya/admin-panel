
package com.adminapp.admin.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data

public class LoanApplications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private Long loan_amount;


    @NonNull
    private String reason;

    @NonNull
    private String suportingDocumentsUrl;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "status_of_app", updatable = true)
    private Status_of_app status_of_app;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id")
    private Users requesterId;

    private LocalDateTime createdAt;

    @PrePersist
    public void defaultTime(){
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public LoanApplications() {
        //TODO Auto-generated constructor stub
    }

    public enum Status_of_app {
        PENDING,
        UNDER_REVIEW,
        APPROVED,
        REJECTED
    }
    
}