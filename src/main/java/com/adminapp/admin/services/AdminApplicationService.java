package com.adminapp.admin.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminapp.admin.models.ServiceProviders;
import com.adminapp.admin.repository.ServiceProviderRepository;

@Service
public class AdminApplicationService {
    
    
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    private Logger log = Logger.getLogger(AdminApplicationService.class.getName());
    

    public long getAllPendingCount(){
        return serviceProviderRepository.countByIsEnabled(false);
    }

    public List <ServiceProviders> getAllProviders(){
        return serviceProviderRepository.findAll();
    }

    public List<ServiceProviders> getPendingAccounts(){
        List <ServiceProviders> applicant = serviceProviderRepository.findByIsEnabled(false);
        return applicant;
    } 

    public void approve(Long serviceProviderId){
        ServiceProviders sp = serviceProviderRepository.findById(serviceProviderId)
                                                        .orElseThrow(()-> new RuntimeException("cannot find the user"));
        
        if (sp.getIsEnabled()) {
            throw new IllegalStateException("User is already approved");
        }

        sp.setIsEnabled(true);
        serviceProviderRepository.save(sp);
        log.log(Level.INFO, "This is log message");
    }

    public List<ServiceProviders>getAllUsers(){
        return serviceProviderRepository.findAll();
    }


}
