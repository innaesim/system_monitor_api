package com.monitor.utility.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.monitor.utility.user.User;
import com.monitor.utility.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AdminInitializer {
    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.password}")
    private String adminPassword;
    @Value("${admin.role}")
    private String role;

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            try {
                userRepository.findByEmail(adminEmail).ifPresentOrElse(u -> new UsernameNotFoundException("Admin Exists"), () -> {
                    User admin = new User();
                    admin.setName("SuperUser");
                    admin.setEmail(adminEmail);
                    admin.setPassword(passwordEncoder.encode(adminPassword));
                    admin.setRole(role);
                    userRepository.save(admin);
                });
                
            } catch (Exception e) {
                log.error("Error {}", e.getMessage());
            }
        };
    }
}
