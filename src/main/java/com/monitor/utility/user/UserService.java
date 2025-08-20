package com.monitor.utility.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.monitor.utility.commons.AuthRequest;
import com.monitor.utility.commons.exceptions.BusinessRuntimeException;
import com.monitor.utility.security.JwtService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
        UserDetailsServiceImpl userDetailsService,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
        ){
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    
    public String authenticate(AuthRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        if (passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())){
            try{
                return jwtService.generateToken(userDetails);
            }
            catch (Exception e){
                log.info("Error Logging In: {}", e.getMessage());
                throw new BusinessRuntimeException("Login Failed");
            }

        }
        else{
            throw new BusinessRuntimeException("Login Failed");
        }
    }

}
