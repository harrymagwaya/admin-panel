package com.adminapp.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adminapp.admin.models.Disability;

@Repository
public interface DisabilityRepository extends JpaRepository<Disability, Long> {

    @Query("Select pwd_table.dis_id, pwd_table.disability_type FROM pwd_table")
    List<Disability> findByDisabilityId();
}
