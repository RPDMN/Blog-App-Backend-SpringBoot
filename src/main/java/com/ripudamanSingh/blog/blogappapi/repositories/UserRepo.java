package com.ripudamanSingh.blog.blogappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ripudamanSingh.blog.blogappapi.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository <User, Integer>{
        // jpa repo => it is used to do manipulation in db. agar na hopti to sab manually karna padta db me.

    Optional<User> findByEmail(String email);
}
