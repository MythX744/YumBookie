package com.example.Project.Controller;


import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.IRecipeService;
import com.example.Project.Service.IUserService;

import com.example.Project.Model.User;
import com.example.Project.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserDao userDao;


    private IUserService userDao1;

    @Autowired
    public UserController(IUserService userDao) {
        this.userDao1 = userDao;
    }

    /*
    @GetMapping("/profile")
    public String showprofile(Model model, @ModelAttribute("user")User user) {
        User existingUser = userDao1.findById(user.getIdUser());
        model.addAttribute("fullName", existingUser.getName());
        model.addAttribute("email", existingUser.getEmail());
        model.addAttribute("password", existingUser.getPassword());
        return "addrecipe";
    }
    */

    @PostMapping("/profile")
    public User updateUser(@ModelAttribute("user") User user, Model model) {
        //still needs some changes like the getId method,
        User existingUser = userDao1.findById(user.getIdUser());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getPassword());
            return userDao.save(existingUser);
        }
        else{
            return null;
        }

    }



    public UserController(UserDao userDao){
        this.userDao=userDao;
    }
    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        User foundUser = userDao.findByEmail(user.getEmail());

        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {

            return "home";
        } else {

            model.addAttribute("message", "Invalid username or password");
            return "login";
        }
    }

}
