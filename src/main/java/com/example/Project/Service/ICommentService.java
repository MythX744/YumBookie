package com.example.Project.Service;

import com.example.Project.Model.Comment;
import com.example.Project.Model.Recipe;

import java.util.List;

public interface ICommentService {
    List<Comment> findAll();
    Comment findById(int id);
    Comment save(Comment comment);
    void deleteById(int id);
    List<Comment> findByRecipe(Recipe recipe);
}
