package com.adminapp.admin.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name = "NG0")
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organisationId;

    private String organisationName;

    @Column(name = "organisation_location")
    private String location;

    @NonNull
    @Column(name = "email")
    private String organisationEmail;

    @NonNull
    @Column(name = "pass")
    private String password;

}
