package com.example.Project.Controller;

import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.IRecipeService;
import com.example.Project.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private IUserService userDao;

    @Autowired
    public UserController(IUserService userDao) {
        this.userDao = userDao;
    }

    /*
    @GetMapping("/profile")
    public String showprofile(Model model, @ModelAttribute("user")User user) {
        User existingUser = userDao.findById(user.getIdUser());
        model.addAttribute("fullName", existingUser.getName());
        model.addAttribute("email", existingUser.getEmail());
        model.addAttribute("password", existingUser.getPassword());
        return "addrecipe";
    }
    */

    @PostMapping("/profile")
    public User updateUser(@ModelAttribute("user") User user, Model model) {
        //still needs some changes like the getId method,
        User existingUser = userDao.findById(user.getIdUser());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getPassword());
            return userDao.save(existingUser);
        }
        else{
            return null;
        }

    }

}
