package com.example.Project.Controller;

import com.example.Project.Model.Favorite;
import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.FavoriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


@Controller
@RequestMapping("/Favorite")
public class FavoriteController {

    private FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
    @GetMapping("/favoriteRecipes")
    public String viewFavorites(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Recipe> favoriteRecipes = favoriteService.findFavoriteRecipesByUser(user);
            model.addAttribute("favoriteRecipes", favoriteRecipes);
        }
        return "profile";
    }

}
