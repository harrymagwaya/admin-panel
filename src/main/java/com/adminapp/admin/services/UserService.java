package com.adminapp.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminapp.admin.models.Users;
import com.adminapp.admin.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }
}
