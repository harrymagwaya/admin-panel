package com.adminapp.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminapp.admin.models.ServiceProviders;
import com.adminapp.admin.repository.AdminRepository;
import com.adminapp.admin.repository.ServiceProviderRepository;

@Service
public class AdminApplicationService {
    
    
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    

    public long getAllPendingCount(){
        return serviceProviderRepository.countByIsEnabledFalse();
    }

    public List <ServiceProviders> getAllProviders(){
        return serviceProviderRepository.findAll();
    }

    public List<ServiceProviders> getPendingAccounts(){
        List <ServiceProviders> applicant = serviceProviderRepository.findByIsEnabledFalseList();
        return applicant;
    } 

    public void approve(Long serviceProviderId){
        ServiceProviders sp = serviceProviderRepository.findById(serviceProviderId)
                                                        .orElseThrow(()-> new RuntimeException("cannot find the user"));
        
        if (sp.getIsEnabled() == true) {
            throw new IllegalStateException("User is already approved");
        }

        sp.setIsEnabled(true);
        serviceProviderRepository.save(sp);
    }


}
