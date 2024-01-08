package com.example.Project.Service;

import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.dao.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    public List<Recipe> findByUser(User user) {
        return recipeDao.findByUser(user);
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
    public List<Recipe> findByCategoryAndUser(String category, User user) {
        return recipeDao.findByCategoryAndUser(category, user);
    }

    @Override
    public List<Recipe> getTrendingRecipes(){
        return recipeDao.findTrendingRecipes();
    }

    @Override
    public void delete(Recipe recipe) {
        recipeDao.delete(recipe);
    }

    @Override
    public Recipe update(Recipe recipe) {
        return recipeDao.save(recipe);
    }

    @Override
    public List<Recipe> searchByKeyword(String keyword) {
        return recipeDao.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Recipe> getFilteredRecipes(String filter) {
        switch (filter) {
            case "1":
                return recipeDao.findAllByOrderByTitleAsc();
            case "2":
                return recipeDao.findAllByOrderByTitleDesc();
            case "3":
                return recipeDao.findAllByOrderByRatingDesc();
            case "4":
                return recipeDao.findAllByOrderByRatingAsc();
            default:
                return recipeDao.findAll();
        }
    }
}