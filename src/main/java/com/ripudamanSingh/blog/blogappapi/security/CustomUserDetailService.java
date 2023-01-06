package com.ripudamanSingh.blog.blogappapi.security;

import com.ripudamanSingh.blog.blogappapi.entities.User;
import com.ripudamanSingh.blog.blogappapi.exceptions.ResourceNotFoundException;
import com.ripudamanSingh.blog.blogappapi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user =  this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user","email",username));
        return user;
    }
}
