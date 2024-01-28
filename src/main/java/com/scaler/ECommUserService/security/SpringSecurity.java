package com.scaler.ECommUserService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*@Order(1)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
        //httpSecurity.authorizeHttpRequests(authorize-> authorize.requestMatchers("/auth/").permitAll());
        httpSecurity.authorizeHttpRequests(authorize-> authorize.anyRequest().permitAll());
        return httpSecurity.build();
    }*/
}
//This is the class for normal userid-password based authentication