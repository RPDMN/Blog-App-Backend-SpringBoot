package com.ripudamanSingh.blog.blogappapi.services;

import com.ripudamanSingh.blog.blogappapi.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    // create
    CategoryDto createCategory(CategoryDto categoryDto);

    // update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    // delete
     void deleteCategory(Integer categoryId);

    // get
    CategoryDto getCategory(Integer categoryId);

    // get All
    List<CategoryDto> getAllCategory();
}
