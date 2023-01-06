package com.ripudamanSingh.blog.blogappapi.payloads;

import com.ripudamanSingh.blog.blogappapi.entities.Category;
import com.ripudamanSingh.blog.blogappapi.entities.User;
import com.ripudamanSingh.blog.blogappapi.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comment = new HashSet<>();

}
