package com.example.postdto.dto;

import com.example.postdto.entity.Comments;
import com.example.postdto.entity.Post;
import com.example.postdto.exception.NotFoundExeption;
import com.example.postdto.service.CommentService;
import com.example.postdto.service.CommentServiceDto;
import com.example.postdto.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoToComment {

    @Autowired
    private CommentServiceDto commentService;

    @Autowired
    private PostService postService;

    public Comments convert(CommentCreateDto commentCreateDto) throws NotFoundExeption {

        Comments comments = commentCreateDto.getId() != null ? commentService.findById(commentCreateDto.getId()) : new Comments();

        comments.setBody(commentCreateDto.getBody());
        comments.setName(commentCreateDto.getName());
        comments.setEmail(commentCreateDto.getEmail());
        comments.setPost(postService.findById(commentCreateDto.getIdPost()));

        return comments;
    }
}
