package com.ripudamanSingh.blog.blogappapi.payloads;

import com.ripudamanSingh.blog.blogappapi.entities.Post;
import com.ripudamanSingh.blog.blogappapi.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class CommentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

}
