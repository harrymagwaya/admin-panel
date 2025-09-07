package com.adminapp.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminapp.admin.models.NgoPosts;

@Repository
public interface NgoPostsRepository extends JpaRepository<NgoPosts, Long> {
    long count();
}
