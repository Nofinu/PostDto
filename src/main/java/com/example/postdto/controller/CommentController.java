package com.example.postdto.controller;

import com.example.postdto.dto.CommentCreateDto;
import com.example.postdto.dto.CommentReadDto;
import com.example.postdto.dto.PostCreateDto;
import com.example.postdto.dto.PostReadDto;
import com.example.postdto.exception.EmptyFieldException;
import com.example.postdto.exception.NotFoundExeption;
import com.example.postdto.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/post_id/{id}")
    public ResponseEntity<List<CommentReadDto>> findByPostId (@PathVariable Integer id) throws NotFoundExeption {
        List<CommentReadDto> commentReadDtos = commentService.findAllByPostId(id);
        return new ResponseEntity<List<CommentReadDto>>(commentReadDtos,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CommentReadDto> create (@RequestBody CommentCreateDto commentCreateDto) throws NotFoundExeption {
        CommentReadDto commentReadDto =  commentService.create(commentCreateDto);
        return new ResponseEntity<CommentReadDto>(commentReadDto, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<CommentReadDto> update (@RequestBody CommentCreateDto commentCreateDto) throws NotFoundExeption, EmptyFieldException {
        if(commentCreateDto.getId() !=null){
            CommentReadDto commentReadDto =  commentService.create(commentCreateDto);
            return new ResponseEntity<CommentReadDto>(commentReadDto, HttpStatus.OK);
        }
        throw new EmptyFieldException();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> delete (@PathVariable Integer id) throws NotFoundExeption {
        if(commentService.delete(id)){
            return new ResponseEntity<String>("Commentaire Suprimé",HttpStatus.OK);
        }
        return new ResponseEntity<String>("Erreure lors de la suppresion",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundExeption.class)
    public ResponseEntity<String> errorNotFound (NotFoundExeption ex){
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<String> errorEmptyField (EmptyFieldException ex){
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NO_CONTENT);
    }
}
