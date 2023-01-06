package com.ripudamanSingh.blog.blogappapi.controllers;

import com.ripudamanSingh.blog.blogappapi.exceptions.ApiException;
import com.ripudamanSingh.blog.blogappapi.payloads.JwtAuthRequest;
import com.ripudamanSingh.blog.blogappapi.payloads.JwtAuthResponse;
import com.ripudamanSingh.blog.blogappapi.payloads.UserDto;
import com.ripudamanSingh.blog.blogappapi.security.CustomUserDetailService;
import com.ripudamanSingh.blog.blogappapi.security.JwtTokenHelper;
import com.ripudamanSingh.blog.blogappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;



    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
    ) throws ApiException{
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username,String password) throws ApiException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

         try{
             this.authenticationManager.authenticate(authenticationToken);
         }catch (BadCredentialsException e){
             System.out.println("Invalid Details !!");
             throw new ApiException("Invalid UserName or Password");
         }

    }

    // register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto registereduser = this.userService.registerNewUser(userDto);

        return new ResponseEntity<UserDto>(registereduser,HttpStatus.CREATED);
    }
}
