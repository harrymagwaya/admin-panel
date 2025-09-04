package com.adminapp.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class ServiceProviders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NonNull
    private String userName;
    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String areaOfExpertise;
    @NonNull
    private String description;
    @NonNull
    private String location;
    @NonNull
    private String providerPicUrl; 

    @NonNull
    private Boolean isEnabled = false;

}
