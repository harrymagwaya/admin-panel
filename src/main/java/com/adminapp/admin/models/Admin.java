package com.adminapp.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long organisationId;

    private String organisationName;

    private String location;

    @NonNull
    private String organisationEmail;

    @NonNull
    private String password;

}
