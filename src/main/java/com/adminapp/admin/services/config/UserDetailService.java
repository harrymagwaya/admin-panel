package com.adminapp.admin.services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adminapp.admin.models.Admin;
import com.adminapp.admin.repository.AdminRepository;

@Service
public class UserDetailService implements UserDetailsService {

   @Autowired
   private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Admin admin = adminRepository.findByEmail(email)
                                    .orElseThrow(()-> new UsernameNotFoundException("User not found"));

       
        return new CustomAdminDetails(admin);


    }
    
}
