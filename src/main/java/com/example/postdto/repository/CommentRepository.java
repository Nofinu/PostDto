package com.example.postdto.repository;

import com.example.postdto.entity.Comments;
import com.example.postdto.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comments,Integer> {
    public List<Comments> findCommentsByPost (Post post);
}
