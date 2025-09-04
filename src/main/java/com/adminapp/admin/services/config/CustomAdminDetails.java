package com.adminapp.admin.services.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.adminapp.admin.models.Admin;
import com.adminapp.admin.models.ServiceProviders;


public class CustomAdminDetails implements UserDetails{

    private final Admin admin;

    CustomAdminDetails(Admin admin){
            this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getOrganisationEmail();
    }
    
}
