package com.adminapp.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_posts")
public class UserPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
}
