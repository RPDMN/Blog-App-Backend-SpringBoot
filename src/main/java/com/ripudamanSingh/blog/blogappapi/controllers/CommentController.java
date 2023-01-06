package com.ripudamanSingh.blog.blogappapi.controllers;

import com.ripudamanSingh.blog.blogappapi.payloads.ApiResponse;
import com.ripudamanSingh.blog.blogappapi.payloads.CommentDto;
import com.ripudamanSingh.blog.blogappapi.repositories.CommentRepo;
import com.ripudamanSingh.blog.blogappapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId,@PathVariable Integer userId){
        CommentDto createComment = this.commentService.createComment(commentDto, postId,userId);
        return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Successfully deleted",true),HttpStatus.OK);
    }

    @GetMapping("/comments/{postId}")
    public ResponseEntity<Set<CommentDto>> getCommentsByPostId(@PathVariable Integer postId){
        Set<CommentDto> commentDtos =  this.commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,@PathVariable Integer commentId){
        CommentDto updateComments = this.commentService.updateComment(commentDto,commentId);
        return new ResponseEntity<>(updateComments,HttpStatus.OK);
    }
}
