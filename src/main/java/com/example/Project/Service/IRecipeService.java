package com.example.Project.Service;

import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IRecipeService {
    List<Recipe> findAll();
    Recipe findById(int id);
    Recipe save(Recipe recipe) throws IOException;
    void deleteById(int id);
    List<Recipe> findByUser(User user);
    List<Recipe> findByCategory(String category);
    List<Recipe> getTrendingRecipes();

}
