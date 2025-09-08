package com.adminapp.admin.services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.adminapp.admin.frontend.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity { // login with vaadin websec configurer

    @Autowired
    private CustomLogin successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);

       http.formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/dashboard", true).failureHandler(successHandler));
           
    }

    @Override
    protected void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
        web.ignoring().requestMatchers("/images/**");
        super.configure(web);
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(User.withUsername("user")
                .password("{noop}userpass")
                .roles("ADMIN")
                .build());
    }

}

