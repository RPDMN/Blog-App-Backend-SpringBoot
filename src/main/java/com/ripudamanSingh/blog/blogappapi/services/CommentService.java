package com.ripudamanSingh.blog.blogappapi.services;

import com.ripudamanSingh.blog.blogappapi.payloads.CommentDto;

import java.util.List;
import java.util.Set;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);

    void deleteComment(Integer commentId);

    Set<CommentDto> getCommentsByPostId(Integer postId);

    CommentDto updateComment(CommentDto commentDto,Integer commentId);
}
