package com.example.Project.dao;

import com.example.Project.Model.Comment;
import com.example.Project.Model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {
    List<Comment> findByRecipe(Recipe recipe);
}
