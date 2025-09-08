
package com.adminapp.admin.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name = "loan_details")
public class LoanApplications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    @NonNull
    @Column(name = "loan_amount")
    private Long loanAmount;

    @NonNull
    @Column(name = "reason")
    private String reason;

    @NonNull
    @Column(name = "supporting_documents")
    private String suportingDocumentsUrl;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "status_of_app", updatable = true)
    @Convert(converter = StatusOfAppConverter.class)
    private StatusOfApp statusOfApp;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requester_id")
    // @Column(name = "")
    private Users requesterId;

    private LocalDateTime createdAt;

    @Column(nullable = true)
    private Long approvedBy;

    @Column(nullable = true)
    private Long rejectedBy;

    @PrePersist
    public void defaultTime() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
            statusOfApp = StatusOfApp.PENDING;
        }
        statusOfApp = StatusOfApp.PENDING;

    }
    public LoanApplications() {
        //TODO Auto-generated constructor stub
    }




    public enum StatusOfApp {
        PENDING,
        UNDER_REVIEW,
        APPROVED,
        REJECTED
    }

    public String getRequesterId() {
        return requesterId != null ? requesterId.getUserId() : null;
    }

 
}