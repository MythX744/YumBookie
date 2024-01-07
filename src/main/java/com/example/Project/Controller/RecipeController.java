package com.example.Project.Controller;

import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.IRecipeService;
import com.example.Project.Service.IUserService;
import com.example.Project.dao.RecipeDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/Recipe")
public class RecipeController {
    private IRecipeService recipeService;
    private IUserService userService;
    @Autowired
    public RecipeController(IRecipeService recipeService){
        this.recipeService=recipeService;
    }
    public RecipeController(IUserService userService){
        this.userService=userService;
    }
    @GetMapping("/loadAddRecipe")
    public String loadAddRecipe(@ModelAttribute("recipe") Recipe recipe){
        return "addRecipe";
    }
    @PostMapping("/addRecipe")
    public String addRecipe(HttpSession session, @ModelAttribute("recipe") Recipe recipe, Model model, @RequestParam("image") MultipartFile file) throws IOException{
        String userEmail = (String) session.getAttribute("userEmail");
        User user = userService.findByEmail(userEmail);
        if (user == null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            recipe.setImage(fileName);
            Recipe savedRecipe = recipeService.save(recipe);
            String uploadDir = "RecipeImages/" + savedRecipe.getIdRecipe();
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save uploaded file: " + fileName);
            }
            model.addAttribute("message", "Created successfully");
            return "redirect:/navigation/home";
        }else {
            return "redirect:/User/loadLogin";
        }
    }

    @GetMapping("/showAllRecipes")
    public String showAllRecipes(Model model) {
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return null;
    }
    @GetMapping("/eachRecipe/{id}")
    public String eachRecipe(@RequestParam("recipeId") int id, Model model){
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "eachRecipe";
    }

}
