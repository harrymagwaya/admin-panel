package com.adminapp.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminapp.admin.models.ServiceProviders;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProviders, Long> {
    Long countByIsEnabledFalse();

    List<ServiceProviders> findByIsEnabledFalseList();
}
