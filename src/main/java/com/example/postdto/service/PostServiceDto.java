package com.example.postdto.service;

import com.example.postdto.entity.Post;
import com.example.postdto.exception.NotFoundExeption;
import com.example.postdto.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceDto {
    @Autowired
    private PostRepository postRepository;
    public Post findById (int id) throws NotFoundExeption {
        Optional<Post> postOptional = postRepository.findById(id);
        if(postOptional.isPresent()){
            return postOptional.get();
        }
        throw new NotFoundExeption();
    }
}
