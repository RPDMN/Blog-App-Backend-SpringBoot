package com.ripudamanSingh.blog.blogappapi.services;

import com.ripudamanSingh.blog.blogappapi.entities.Post;
import com.ripudamanSingh.blog.blogappapi.payloads.PostDto;
import com.ripudamanSingh.blog.blogappapi.payloads.PostResponse;

import java.util.List;
public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer category);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts
    PostResponse getAllPost(Integer pageNumber , Integer pageSize,String sortBy,String sortDir);

    //get single post
    PostDto getPostById(Integer postId);

    //get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all posts by user
    List<PostDto> getPostByUser(Integer userId);

    //search post
    List<PostDto> searchPost(String keyword);
}
