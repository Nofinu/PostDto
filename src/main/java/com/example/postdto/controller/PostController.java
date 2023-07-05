package com.example.postdto.controller;

import com.example.postdto.dto.PostCreateDto;
import com.example.postdto.dto.PostReadDto;
import com.example.postdto.exception.EmptyFieldException;
import com.example.postdto.exception.NotFoundExeption;
import com.example.postdto.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostReadDto>> findAll() {
        List<PostReadDto> postReadDtos = postService.findAll();
        return new ResponseEntity<List<PostReadDto>>(postReadDtos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostReadDto> findById(@PathVariable Integer id) throws NotFoundExeption {
        PostReadDto postReadDto = postService.findByIdRead(id);
        return new ResponseEntity<PostReadDto>(postReadDto,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<PostReadDto> create(@RequestBody PostCreateDto postCreateDto) throws NotFoundExeption {
        PostReadDto post = postService.create(postCreateDto);
        return new ResponseEntity<PostReadDto>(post, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<PostReadDto> update(@RequestBody PostCreateDto postCreateDto) throws NotFoundExeption, EmptyFieldException {
        if (postCreateDto.getId() != null) {
            PostReadDto post = postService.create(postCreateDto);
            return new ResponseEntity<PostReadDto>(post, HttpStatus.OK);
        }
        throw new EmptyFieldException();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws NotFoundExeption {
        if (postService.delete(id)) {
            return new ResponseEntity<String>("Post Suprimé", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Erreure lors de la suppresion", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NotFoundExeption.class)
    public ResponseEntity<String> errorNotFound(NotFoundExeption ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<String> errorEmptyField(EmptyFieldException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NO_CONTENT);
    }
}
