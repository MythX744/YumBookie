package com.example.Project.Controller;

import com.example.Project.Model.Favorite;
import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.FavoriteService;
import com.example.Project.Service.RecipeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/Favorite")
public class FavoriteController {

    private FavoriteService favoriteService;
    private RecipeService recipeService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService, RecipeService recipeService) {
        this.favoriteService = favoriteService;
        this.recipeService = recipeService;
    }

    @GetMapping("/favoriteRecipes")
    public String viewFavorites(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Recipe> favoriteRecipes = favoriteService.findFavoriteRecipesByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("recipes", favoriteRecipes);
        model.addAttribute("viewMode", "favorites");
        return "profile";
    }
    @GetMapping("/toggleFavorite")
    public String toggleFavoriteStatus(@RequestParam("recipeId") int recipeId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Recipe recipe = recipeService.findById(recipeId);
        favoriteService.toggleFavoriteStatus(recipe, user);
        return "redirect:/navigation/home";
    }


}
