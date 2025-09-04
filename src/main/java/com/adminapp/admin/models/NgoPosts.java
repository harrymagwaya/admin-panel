package com.adminapp.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class NgoPosts {
    @Id
    private long id;
}
