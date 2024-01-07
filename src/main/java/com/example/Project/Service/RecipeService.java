package com.example.Project.Service;

import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.dao.RecipeDao;
import com.example.Project.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService implements IRecipeService {
    private RecipeDao recipeDao;
    private UserDao userDao;
    @Autowired
    public RecipeService(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }
    public RecipeService(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public List<Recipe> findAll() {
        return recipeDao.findAll();
    }

    @Override
    public Recipe findById(int id) {
            Optional<Recipe> result = recipeDao.findById(id);

            Recipe recipe = null;

            if (result.isPresent()) {
                recipe = result.get();
            }
            else {
                throw new RuntimeException("Did not find employee id - " + id);
            }
            return recipe;
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeDao.save(recipe);
    }

    @Override
    public void deleteById(int id) {
        recipeDao.deleteById(id);
    }

    @Override
    public List<Recipe> findByCategory(String category) {
        return recipeDao.findByCategory(category);
    }

    @Override
    public List<Recipe> findByUser(User user) {
        return recipeDao.findByUser(user);
    }
}
