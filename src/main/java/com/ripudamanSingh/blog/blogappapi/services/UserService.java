package com.ripudamanSingh.blog.blogappapi.services;
import java.util.List;
import com.ripudamanSingh.blog.blogappapi.entities.User;
import com.ripudamanSingh.blog.blogappapi.payloads.UserDto;
import org.springframework.stereotype.Service;

public interface UserService {

   UserDto registerNewUser(UserDto user);
   UserDto createUser(UserDto user);
   UserDto updateUser(UserDto user,Integer userId);
   UserDto getUserById(Integer userId);

   List<UserDto> getAllUsers();
   void deleteUser(Integer userId);
}
