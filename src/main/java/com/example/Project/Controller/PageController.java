package com.example.Project.Controller;
import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.IRecipeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/navigation")
public class PageController {

    private IRecipeService recipeService;
    @Autowired
    public PageController(IRecipeService recipeService){
        this.recipeService=recipeService;
    }

    @GetMapping("/home")
    public String LoadHome(Model theModel){
        List<Recipe> recipes = recipeService.findAll();
        if (recipes.size() > 3) {
            recipes = recipes.subList(0, 3); // Limit to first 3 recipes
        }
        theModel.addAttribute("recipes", recipes);
        return "home";
    }

    @GetMapping("/lunch")
    public String LoadLunch(Model theModel){
        List<Recipe> recipes = recipeService.findByCategory("Lunch");
        theModel.addAttribute("recipes", recipes);
        return "allrecipes";
    }

    @GetMapping("/breakfast")
    public String LoadBreakfast(Model theModel){
        List<Recipe> recipes = recipeService.findByCategory("breakfast");
        theModel.addAttribute("recipes", recipes);
        return "allrecipes";
    }

    @GetMapping("/snack")
    public String LoadSnake(Model theModel){
        List<Recipe> recipes = recipeService.findByCategory("snack");
        theModel.addAttribute("recipes", recipes);
        return "allrecipes";
    }

    @GetMapping("/dinner")
    public String LoadDinner(Model theModel){
        List<Recipe> recipes = recipeService.findByCategory("dinner");
        theModel.addAttribute("recipes", recipes);
        return "allrecipes";
    }

    /*@GetMapping("/loadProfile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //List<Recipe> recipes = recipeService.findByUserID(user.getIdUser());
        System.out.println(user.getIdUser());
        //model.addAttribute("recipes", recipes);
        model.addAttribute("user", user);
        return "profile";
    }*/

    @GetMapping("/loadProfile")
    public String getRecipes(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Recipe> recipes = recipeService.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("recipes", recipes);
        return "profile";
    }

    @GetMapping("/loadAllRecipes")
    public String loadAllRecipes(Model model){
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "allrecipes";
    }


}
