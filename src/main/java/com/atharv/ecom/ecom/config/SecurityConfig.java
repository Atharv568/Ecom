package com.atharv.ecom.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(customizer -> customizer.disable());
       http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
       http.formLogin(Customizer.withDefaults());
       http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
//    public UserDetailsService userDetailsService(){
////        UserDetails user1= User.withDefaultPasswordEncoder()
////                .username("prakhar")
////                .password("Prakhar@123")
////                .roles("USER")
////                .build();
////
////        UserDetails user2= User.withDefaultPasswordEncoder()
////                .username("mridula")
////                .password("Mridula@123")
////                .roles("ADMIN")
////                .build();
//
//        //working with data base
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }


    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
