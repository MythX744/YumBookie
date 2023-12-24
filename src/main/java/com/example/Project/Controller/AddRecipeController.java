package com.example.Project.Controller;

import com.example.Project.Model.Recipe;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class AddRecipeController {

    @RequestMapping("/addrecipe")
    public String showAddRecipe(Model model){
        return "addrecipe";
    }

    @RequestMapping("/addNewRecipe")
    public String addnewRecipe(Recipe newRecipe, BindingResult bindingResult, Model model){

        return "addrecipe";
    }




}
