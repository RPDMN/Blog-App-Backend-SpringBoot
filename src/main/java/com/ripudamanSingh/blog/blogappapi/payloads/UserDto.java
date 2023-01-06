package com.ripudamanSingh.blog.blogappapi.payloads;

import com.ripudamanSingh.blog.blogappapi.entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {


    private int id;
    @NotEmpty
    @Size(min=4,max=25,message = "name less than 4")
    private String name;
    @Email(message = "Email Address is not valid")
    private String email;
    @NotEmpty
    @Size(min=3,max=10,message = "password should be of min 3 chars and max 10 chars")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles  = new HashSet<>();

}
