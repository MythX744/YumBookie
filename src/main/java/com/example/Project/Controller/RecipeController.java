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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute Recipe recipe, @RequestParam("image") MultipartFile[] files, RedirectAttributes redirectAttributes, HttpSession session) {
        List<String> fileNames = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        // Process each image file
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String fileName = saveImageFile(file); // Save the image and get the file name
                    fileNames.add(fileName); // Add file name to the list
                } catch (IOException e) {
                    // Handle the case where the image save fails
                    redirectAttributes.addFlashAttribute("errorMessage", "Error saving image: " + file.getOriginalFilename());
                    return "redirect:/navigation/loadAddRecipe"; // Redirect back to the form page
                }
            }
        }

        // Concatenate file names and set to the recipe's image attribute
        String imageFilesConcatenated = String.join(",", fileNames); // Concatenate with comma delimiter
        recipe.setImage(imageFilesConcatenated);
        recipe.setUser(user); // Set the user
        // Save the recipe object
        try {
            recipeService.save(recipe);
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("errorMessage", "Error saving recipe");
            return "redirect:/navigation/loadAddRecipe"; // Redirect back to the form page
        }
        redirectAttributes.addFlashAttribute("successMessage", "Recipe added successfully!");
        return "redirect:/navigation/home"; // Redirect to a success page
    }

    private String saveImageFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "src/main/resources/static/RecipeImages/"; // Adjust the path as needed
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
    @GetMapping("/showUserRecipes")
    public String showUserRecipes(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Recipe> userRecipes = recipeService.findByUser(user);
            model.addAttribute("recipes", userRecipes);
            return "profile";
        } else {
            return "redirect:/User/loadLogin";
        }
    }
    @GetMapping("/showAllRecipes")
    public String showAllRecipes(Model model) {
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return null;
    }

}
