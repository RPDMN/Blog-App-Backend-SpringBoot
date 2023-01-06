package com.ripudamanSingh.blog.blogappapi.repositories;

import com.ripudamanSingh.blog.blogappapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
