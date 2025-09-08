package com.adminapp.admin.services.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogin implements AuthenticationFailureHandler {

    // @Override
    // public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException{
    //      String username = auth.getName();

         
    //         response.sendRedirect("/dashboard");
         
    // }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException {

        System.out.println("❌ Login failed: " + exception.getMessage());
        response.sendRedirect("/login?error=true");
    }
    
}

/*
 * @Component
 * public class CustomLoginSuccessHandler implements
 * AuthenticationSuccessHandler {
 * 
 * @Override
 * public void onAuthenticationSuccess(
 * HttpServletRequest request,
 * HttpServletResponse response,
 * Authentication authentication) throws IOException {
 * 
 * String username = authentication.getName();
 * AuditLogger.logLogin(username); // 🧠 Centralized audit
 * 
 * boolean isAdmin = authentication.getAuthorities().stream()
 * .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
 * 
 * if (isAdmin) {
 * response.sendRedirect("/admin-dashboard");
 * } else {
 * response.sendRedirect("/dashboard");
 * }
 * }
 * }
 * 
 */