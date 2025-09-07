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
@Table(name = "service_provider")
public class ServiceProviders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long providerId;

    @Column(name = "provider_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "password_")
    private String password;

    @NonNull
    @Column(name = "area_of_expertise")
    private String areaOfExpertise;

    @Column(name = "description_")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "picture_url")
    private String pictureUrl; 

    @NonNull
    @Column(name = "is_enabled")
    private Boolean isEnabled = false;

}
