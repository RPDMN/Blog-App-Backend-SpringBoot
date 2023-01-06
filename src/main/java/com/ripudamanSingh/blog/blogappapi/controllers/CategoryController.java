package com.ripudamanSingh.blog.blogappapi.controllers;

import com.ripudamanSingh.blog.blogappapi.entities.Category;
import com.ripudamanSingh.blog.blogappapi.payloads.ApiResponse;
import com.ripudamanSingh.blog.blogappapi.payloads.CategoryDto;
import com.ripudamanSingh.blog.blogappapi.payloads.UserDto;
import com.ripudamanSingh.blog.blogappapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ripudamanSingh.blog.blogappapi.payloads.ApiResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
           CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
           return new  ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
        CategoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<>(updateCategoryDto,HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId){
         this.categoryService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("User Successfully deleted",true),HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId){
        CategoryDto getCategoryDto = this.categoryService.getCategory(catId);
        return new ResponseEntity<>(getCategoryDto,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllUsers(){

        return ResponseEntity.ok(this.categoryService.getAllCategory());

    }
}
