package com.example.postdto.dto;

import com.example.postdto.entity.Comments;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentDto {
    public CommentReadDto convert(Comments comments){
        CommentReadDto commentDto = new CommentReadDto();
        commentDto.setBody(comments.getBody());
        commentDto.setName(comments.getName());
        commentDto.setEmail(comments.getEmail());
        return commentDto;
    }
}
