package com.example.Project.Controller;
import com.example.Project.Model.Comment;
import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.ICommentService;
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
    private ICommentService commentService;
    @Autowired
    public PageController(IRecipeService recipeService, ICommentService commentService){
        this.recipeService=recipeService;
        this.commentService=commentService;
    }


    @GetMapping("/home")
    public String LoadHome(Model theModel){
        List<Recipe> recipes = recipeService.findAll();
        if (recipes.size() > 3) {
            recipes = recipes.subList(0, 3); // Limit to first 3 recipes
        }
        theModel.addAttribute("recipes", recipes);
        List<Recipe> trendingRecipes = recipeService.getTrendingRecipes();
        if (trendingRecipes.size() > 3) {
            trendingRecipes = trendingRecipes.subList(0, 3); // Limit to first 3 recipes
        }
        theModel.addAttribute("trendingRecipes", trendingRecipes);
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

    @GetMapping("/loadProfile")
    public String getRecipes(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Recipe> recipes = recipeService.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("recipes", recipes);
        return "profile";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam("filter") String category, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(category.equals("all")){
            return "redirect:/navigation/loadProfile";
        }else{
            List<Recipe> recipes = recipeService.findByCategoryAndUser(category, user);
            model.addAttribute("user", user);
            model.addAttribute("recipes", recipes);
            return "profile";
        }
    }

    @GetMapping("/loadAllRecipes")
    public String loadAllRecipes(Model model){
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "allrecipes";
    }
    @GetMapping("/loadAddRecipe")
    public String loadAddRecipe(Model model){
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "addRecipe";
    }
    @GetMapping("/loadEachRecipe")
    public String loadEachRecipe(@RequestParam("id") int id, Model model){
        Recipe recipe = recipeService.findById(id);
        List<Comment> comments = commentService.findByRecipe(recipe);
        model.addAttribute("comments", comments);
        model.addAttribute("recipe", recipe);
        return "eachRecipe";
    }

}
