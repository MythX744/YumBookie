package com.example.Project.Controller;

import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.UserService;
import com.example.Project.dao.RecipeDao;
import com.example.Project.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ProfileController {

    private UserDao userDao;

    @Autowired
    public ProfileController(UserDao userDao){
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public String showprofile(Model model){
        return "addrecipe";
    }

    /*@PostMapping("/profile")
    public String updateUser(@ModelAttribute("user") User user, Model model){
        User existingUser = UserService.findById();

        if(existingUser != null){
            existingUser.setName(user.getName());
            existingUser.setPassword(user.getPassword());
            userDao.save(existingUser);
            model.addAttribute("message", "Updated successfully");
        }

        return "profile";
    }

     */


}
