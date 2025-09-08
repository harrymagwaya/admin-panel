package com.adminapp.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adminapp.admin.models.Users;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<Users, String>{
   
    // @Query(value = "Select u.user_id, u.area_of_interest, u.has_disability from users u", nativeQuery = true)
    // List <Object[]> getObjectsNative();  
}
