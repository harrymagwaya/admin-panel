package com.adminapp.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminapp.admin.models.LoanApplications;
import com.adminapp.admin.models.LoanApplications.Status_of_app;

import java.util.List;



@Repository
public interface LoanRepository extends JpaRepository<LoanApplications,  Long>{
    
    List <LoanApplications> findAll();

    List <LoanApplications>  findByStatus_of_app(Status_of_app status_of_app);

    long countByStatus_of_app(Status_of_app status_of_app);

    long countById();

}
