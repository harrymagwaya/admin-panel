package com.adminapp.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminapp.admin.models.UserPosts;

@Repository
public interface UserPostsRepository extends JpaRepository<UserPosts, Long> {
    long count();
}
