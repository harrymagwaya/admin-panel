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
@Table(name = "users")
public class Users {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private String userId;

    
    @Column(name = "phone_num", nullable = true)
    private String phone;

    @NonNull
    private String areaOfInterest;

    @Column(nullable = true)
    private Boolean hasDisability;

    



}
