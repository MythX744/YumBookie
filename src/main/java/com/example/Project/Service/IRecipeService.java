package com.example.Project.Service;

import com.example.Project.Model.Recipe;

import java.io.IOException;
import java.util.List;
public interface IRecipeService {
    List<Recipe> findAll();
    Recipe findById(int id);
    Recipe save(Recipe recipe) throws IOException;
    void deleteById(int id);

}
