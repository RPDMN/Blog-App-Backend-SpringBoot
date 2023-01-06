package com.ripudamanSingh.blog.blogappapi.repositories;

import com.ripudamanSingh.blog.blogappapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
