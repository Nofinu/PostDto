package com.example.postdto.dto;

import com.example.postdto.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostToPostDto {

    public PostReadDto convert(Post post){
        PostReadDto postDto = new PostReadDto();
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }
}
