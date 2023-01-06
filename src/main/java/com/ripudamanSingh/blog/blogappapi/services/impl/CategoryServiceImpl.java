package com.ripudamanSingh.blog.blogappapi.services.impl;

import com.ripudamanSingh.blog.blogappapi.exceptions.ResourceNotFoundException;
import com.ripudamanSingh.blog.blogappapi.payloads.CategoryDto;
import com.ripudamanSingh.blog.blogappapi.payloads.UserDto;
import com.ripudamanSingh.blog.blogappapi.repositories.CategoryRepo;
import com.ripudamanSingh.blog.blogappapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.ripudamanSingh.blog.blogappapi.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
         Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));

         cat.setCategoryTitle(categoryDto.getCategoryTitle());
         cat.setCategoryDescription(categoryDto.getCategoryDescription());

         Category updatedCat = this.categoryRepo.save(cat);

        return this.modelMapper.map(updatedCat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
       Category cat = this.categoryRepo.findById(categoryId)
               .orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));

       this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));
        
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> cat = this.categoryRepo.findAll();
        List<CategoryDto> catDto =  cat.stream().map((ca) -> this.modelMapper.map(ca,CategoryDto.class)).collect(Collectors.toList());

        return catDto;
    }
}
