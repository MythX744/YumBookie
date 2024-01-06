package com.example.Project.Controller;

import com.example.Project.Model.Recipe;
import com.example.Project.dao.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {


    private RecipeDao recipeDao;

    @Autowired
    public RecipeController(RecipeDao recipeDao){
        this.recipeDao=recipeDao;
    }


    @GetMapping("/addrecipe")
    public String showAddRecipe(@ModelAttribute("recipe") Recipe recipe){
        return "addrecipe";
    }


    @PostMapping("/addrecipe")
    public String addnewRecipe(@ModelAttribute("recipe") Recipe recipe, Model model){
        recipeDao.save(recipe);
        model.addAttribute("message", "Created successfully");
        return "addrecipe";
    }

    @PostMapping("/profile")
    public String listUserRecipes(Model model) {
        List<Recipe> recipes = recipeDao.findAll();
        model.addAttribute("recipes", recipes);
        return "profile";
    }

}
