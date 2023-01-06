package com.ripudamanSingh.blog.blogappapi.services.impl;

import com.ripudamanSingh.blog.blogappapi.entities.Comment;
import com.ripudamanSingh.blog.blogappapi.entities.Post;
import com.ripudamanSingh.blog.blogappapi.entities.User;
import com.ripudamanSingh.blog.blogappapi.exceptions.ResourceNotFoundException;
import com.ripudamanSingh.blog.blogappapi.payloads.CommentDto;
import com.ripudamanSingh.blog.blogappapi.repositories.CommentRepo;
import com.ripudamanSingh.blog.blogappapi.repositories.PostRepo;
import com.ripudamanSingh.blog.blogappapi.repositories.UserRepo;
import com.ripudamanSingh.blog.blogappapi.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post","post id",postId));
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","user id",userId));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);

        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
      Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment","comment id",commentId));
      this.commentRepo.delete(comment);
    }

    @Override
    public Set<CommentDto> getCommentsByPostId(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post","post id",postId));

        Set<Comment> comments = post.getComments();
        Set<CommentDto> commentDto = comments.stream().map((comment) -> this.modelMapper.map(comment,CommentDto.class)).collect(Collectors.toSet());

        return commentDto;
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment","comment id",commentId));
        comment.setContent(commentDto.getContent());

        Comment savedComment = this.commentRepo.save(comment);

        return  this.modelMapper.map(savedComment,CommentDto.class);
    }
}
