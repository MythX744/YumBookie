package com.example.Project.dao;

import com.example.Project.Model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeDao extends JpaRepository<Recipe, Integer> {
}
