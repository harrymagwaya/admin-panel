package com.adminapp.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Users {
   
    @Id
    private String id;

    @NonNull
    private String gender;

    private String userProfession;

    private boolean hasDisability;


}
