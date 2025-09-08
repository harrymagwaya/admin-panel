package com.adminapp.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adminapp.admin.models.Disability;

@Repository
public interface DisabilityRepository extends JpaRepository<Disability, Long> {

    // @Query(value = "Select dis_id, disability_type from pwd_table", nativeQuery = true)
    // List<Object[]> getObjectsNative();

    long count();
}
