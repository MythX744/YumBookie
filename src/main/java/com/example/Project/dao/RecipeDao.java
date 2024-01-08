package com.example.Project.dao;

import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeDao extends JpaRepository<Recipe, Integer> {
    @Query("SELECT r FROM Recipe r LEFT JOIN r.comments c GROUP BY r ORDER BY SUM(c.ratings) DESC")
    List<Recipe> findTrendingRecipes();
    List<Recipe> findByUser(User user);
    List<Recipe> findByCategory(String category);
    List<Recipe> findByCategoryAndUser(String category, User user);
    List<Recipe> findByTitleContainingIgnoreCase(String keyword);
    List<Recipe> findAllByOrderByTitleAsc();
    List<Recipe> findAllByOrderByTitleDesc();
    List<Recipe> findAllByOrderByRatingDesc();
    List<Recipe> findAllByOrderByRatingAsc();

    @Query("SELECT AVG(c.ratings) FROM Comment c WHERE c.recipe.idRecipe = :recipeId")
    Optional<Double> findAverageRatingByRecipeId(int recipeId);
}
