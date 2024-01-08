package com.example.Project.dao;

import com.example.Project.Model.Favorite;
import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteDao extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser(User user);

    // Custom method to delete a favorite entry
    @Transactional
    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.recipe = :recipe AND f.user = :user")
    void deleteFavoriteByRecipeAndUser(Recipe recipe, User user);
    boolean existsByUserAndRecipe(User user, Recipe recipe);

}
