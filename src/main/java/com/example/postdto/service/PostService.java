package com.example.postdto.service;

import com.example.postdto.dto.PostCreateDto;
import com.example.postdto.dto.PostDtoToPost;
import com.example.postdto.dto.PostReadDto;
import com.example.postdto.dto.PostToPostDto;
import com.example.postdto.entity.Post;
import com.example.postdto.exception.NotFoundExeption;
import com.example.postdto.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostToPostDto postToPostDto;

    @Autowired
    private PostDtoToPost postDtoToPost;

    public PostReadDto create(PostCreateDto postCreateDto) throws NotFoundExeption {
        Post post = postDtoToPost.convert(postCreateDto);
        postRepository.save(post);
        return postToPostDto.convert(post);
    }

    public boolean delete (int id) throws NotFoundExeption {
        Post post = findById(id);
        postRepository.delete(post);
        return true;
    }

    public Post findById (int id) throws NotFoundExeption {
        Optional<Post> postOptional = postRepository.findById(id);
        if(postOptional.isPresent()){
            return postOptional.get();
        }
        throw new NotFoundExeption();
    }

    public PostReadDto findByIdRead (int id) throws NotFoundExeption {
        return postToPostDto.convert(findById(id));
    }

    public List<PostReadDto> findAll(){
        List<Post> posts = (List<Post>) postRepository.findAll();
        List<PostReadDto> postReadDtos = new ArrayList<>();
        if(!posts.isEmpty()){
            for (Post p:posts) {
                postReadDtos.add(postToPostDto.convert(p));
            }
        }
        return postReadDtos;
    }
}
