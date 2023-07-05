package com.example.postdto.dto;

import com.example.postdto.entity.Post;
import com.example.postdto.exception.NotFoundExeption;
import com.example.postdto.service.PostService;
import com.example.postdto.service.PostServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostDtoToPost {
    @Autowired
    private PostServiceDto postService;

    public Post convert(PostCreateDto postDto) throws NotFoundExeption {

        Post post = postDto.getId() != null ? postService.findById(postDto.getId()) : new Post();

        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        return post;
    }
}
