package com.ripudamanSingh.blog.blogappapi.controllers;

import com.ripudamanSingh.blog.blogappapi.config.AppConstants;
import com.ripudamanSingh.blog.blogappapi.payloads.ApiResponse;
import com.ripudamanSingh.blog.blogappapi.payloads.PostResponse;
import com.ripudamanSingh.blog.blogappapi.services.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import com.ripudamanSingh.blog.blogappapi.payloads.PostDto;
import com.ripudamanSingh.blog.blogappapi.payloads.PostDto;
import com.ripudamanSingh.blog.blogappapi.services.PostService;
import com.ripudamanSingh.blog.blogappapi.entities.Post;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto createdPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(this.modelMapper.map(createdPost,PostDto.class), HttpStatus.CREATED);
    }

    //get by User

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId){
        List<PostDto> posts= this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getUserByCategory(@PathVariable Integer categoryId){
        List<PostDto> getPost= this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(getPost,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required=false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required=false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
    ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);

    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto getPost = this.postService.getPostById(postId);
        return new ResponseEntity<>(getPost,HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId){
        PostDto post = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(post,HttpStatus.OK);
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Deleted the Post",true),HttpStatus.OK);
    }

    // Search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
            @PathVariable("keywords") String keywords
    ){
      List<PostDto> result = this.postService.searchPost(keywords);
      return new ResponseEntity< List<PostDto>>(result,HttpStatus.OK);
    }

    // post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage (
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            )throws IOException {

        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path,image);
        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    @GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException{
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }


}
