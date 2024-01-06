package com.example.Project.Controller;

import com.example.Project.Model.Recipe;
import com.example.Project.Service.IRecipeService;
import com.example.Project.dao.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/Recipe")
public class RecipeController {

    private IRecipeService recipeService;

    @Autowired
    public RecipeController(IRecipeService recipeService){
        this.recipeService=recipeService;
    }


    @GetMapping("/loadAddRecipe")
    public String loadAddRecipe(@ModelAttribute("recipe") Recipe recipe){
        return "addRecipe";
    }


    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute("recipe") Recipe recipe, Model model, @RequestParam("image") MultipartFile file)
            throws IOException, SerialException, SQLException{
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        recipe.setImage(blob);
        recipeService.save(recipe);
        model.addAttribute("message", "Created successfully");
        return "redirect:/navigation/home";
    }

    @PostMapping("/profile")
    public String listUserRecipes(Model model) {
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "profile";
    }

}
