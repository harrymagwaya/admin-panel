package com.adminapp.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminapp.admin.models.LoanApplications;
import com.adminapp.admin.models.LoanApplications.StatusOfApp;


import java.util.List;



@Repository
public interface LoanRepository extends JpaRepository<LoanApplications,  Long>{
    
    List <LoanApplications> findAll();

    List <LoanApplications>  findByStatusOfApp(StatusOfApp status_of_app);

    long countByStatusOfApp(StatusOfApp statusOfApp);

    long count();

}
