package com.example.postdto.service;

import com.example.postdto.dto.CommentCreateDto;
import com.example.postdto.dto.CommentDtoToComment;
import com.example.postdto.dto.CommentReadDto;
import com.example.postdto.dto.CommentToCommentDto;
import com.example.postdto.entity.Comments;
import com.example.postdto.entity.Post;
import com.example.postdto.exception.NotFoundExeption;
import com.example.postdto.repository.CommentRepository;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentDtoToComment commentDtoToComment;

    @Autowired
    private CommentToCommentDto commentToCommentDto;

    @Autowired
    private PostService postService;

    public CommentReadDto create (CommentCreateDto commentCreateDto) throws NotFoundExeption {
        Comments comments = commentDtoToComment.convert(commentCreateDto);
        commentRepository.save(comments);
        return commentToCommentDto.convert(comments);
    }

    public boolean delete (int id) throws NotFoundExeption {
        Comments comments = findById(id);
        commentRepository.delete(comments);
        return true;
    }

    public Comments findById (int id) throws NotFoundExeption {
        Optional<Comments> commentsOptional = commentRepository.findById(id);
        if(commentsOptional.isPresent()){
            return commentsOptional.get();
        }
        throw new NotFoundExeption();
    }

    public List<CommentReadDto> findAllByPostId(int postId) throws NotFoundExeption {
        Post post = postService.findById(postId);
        List<Comments> comments = commentRepository.findCommentsByPost(post);
        List<CommentReadDto> commentReadDtos = new ArrayList<>();
        if(!comments.isEmpty()){
            for (Comments c:comments) {
                commentReadDtos.add(commentToCommentDto.convert(c));
            }
        }
        return commentReadDtos;
    }
}
