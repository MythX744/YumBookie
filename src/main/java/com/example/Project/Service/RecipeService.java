package com.example.Project.Service;

import com.example.Project.Model.Recipe;
import com.example.Project.dao.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecipeService implements IRecipeService {
    private RecipeDao recipeDao;
    @Autowired
    public RecipeService(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }
    @Override
    public List<Recipe> findAll() {
        return recipeDao.findAll();
    }

    @Override
    public Recipe findById(int id) {
        return recipeDao.findById(id).get();
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeDao.save(recipe);
    }

    @Override
    public void deleteById(int id) {

    }
}
