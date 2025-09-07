package com.adminapp.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminapp.admin.models.LoanApplications;
import com.adminapp.admin.models.LoanApplications.StatusOfApp;

import com.adminapp.admin.repository.LoanRepository;

@Service
public class AdminLoanAppService {
    
    
    @Autowired
    private LoanRepository loanRepository;

    public long getPendingLoans(){
        return loanRepository.countByStatusOfApp(StatusOfApp.PENDING);
    }
    public long getApprovedLoans(){
        return loanRepository.countByStatusOfApp(StatusOfApp.APPROVED);
    }
    public long getRejectedLoans(){
        return loanRepository.countByStatusOfApp(StatusOfApp.REJECTED);
    }

    public long countLoans(){
        return loanRepository.count();
    }

    public List<LoanApplications> getAllLoans(){
        return loanRepository.findAll();
    }

    public List<LoanApplications> getListPendingLoans(){
        List <LoanApplications> applicants = loanRepository.findByStatusOfApp(StatusOfApp.PENDING);
        return applicants;
    }
    public List<LoanApplications> getListApprovedLoans(){
        List <LoanApplications> applicants = loanRepository.findByStatusOfApp(StatusOfApp.APPROVED);
        return applicants;
    }
    public List<LoanApplications> getListRejectedLoans(){
        List <LoanApplications> applicants = loanRepository.findByStatusOfApp(StatusOfApp.REJECTED);
        return applicants;
    }

    public void approve(Long loan){
        LoanApplications loans = loanRepository.findById(loan)
                                        .orElseThrow(()-> new RuntimeException("cannot find loan"));
        if (loans.getStatusOfApp() == StatusOfApp.APPROVED) {
            throw new IllegalStateException("loan already approved");
        }
        else if (loans.getStatusOfApp() == StatusOfApp.REJECTED) {
            throw new IllegalStateException("Loan was already rejected");
        }
        
        loans.setStatusOfApp(StatusOfApp.APPROVED);

        loanRepository.save(loans);
        
    }

    public void reject(Long loan){
        LoanApplications loans = loanRepository.findById(loan)
                                        .orElseThrow(()-> new RuntimeException("cannot find loan"));
        if (loans.getStatusOfApp() == StatusOfApp.APPROVED) {
            throw new IllegalStateException("loan already approved");
        }
        
        loans.setStatusOfApp(StatusOfApp.REJECTED);

        loanRepository.save(loans);  
    }
}
