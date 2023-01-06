package com.ripudamanSingh.blog.blogappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ripudamanSingh.blog.blogappapi.entities.Category;
import com.ripudamanSingh.blog.blogappapi.entities.User;
import com.ripudamanSingh.blog.blogappapi.entities.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface PostRepo extends JpaRepository<Post,Integer> {

      List<Post> findByUser(User user);
      List<Post> findByCategory(Category category);

      // Search
    // List<Post> findByTitleContaining(String Title);
      @Query("select p from Post p where p.title like :key")
      List<Post> searchByTitle(@Param("key") String title);
}
