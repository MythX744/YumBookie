package com.example.Project.dao;

import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeDao extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByUser(User user);
    List<Recipe> findByCategory(String category);
}
