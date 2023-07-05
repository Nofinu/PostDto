package com.example.postdto.service;

import com.example.postdto.entity.Comments;
import com.example.postdto.exception.NotFoundExeption;
import com.example.postdto.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceDto {
    @Autowired
    private CommentRepository commentRepository;

    public Comments findById (int id) throws NotFoundExeption {
        Optional<Comments> commentsOptional = commentRepository.findById(id);
        if(commentsOptional.isPresent()){
            return commentsOptional.get();
        }
        throw new NotFoundExeption();
    }
}
