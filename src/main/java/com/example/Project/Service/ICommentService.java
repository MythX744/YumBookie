package com.example.Project.Service;

import com.example.Project.Model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> findAll();
    Comment findById(int id);
    Comment save(Comment comment);
    void deleteById(int id);
}
