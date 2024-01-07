package com.example.Project.Controller;


import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.IRecipeService;
import com.example.Project.Service.IUserService;

import com.example.Project.Model.User;
import com.example.Project.dao.UserDao;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Set;


@Controller
@SessionAttributes("user")
@RequestMapping("/User")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loadProfile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Set<Recipe> recipes = user.getRecipes();
        model.addAttribute("recipes", recipes);
        model.addAttribute("user", user);
        return "profile";
    }


    @PostMapping("/profile")
    public User updateUser(@ModelAttribute("user") User user, Model model) {
        userService.update(user);
        return user;
    }

    @GetMapping("/loadLogin")
    public String getLoginPage(Model leModel) {
        User theUser = new User();
        leModel.addAttribute("user", theUser);
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @ModelAttribute("user") User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (userService.validateUser(email, password)) {
            session.setAttribute("username", user);
            return "redirect:/navigation/home";
        } else {
            System.out.println("Invalid credentials");
            return "redirect:/User/loadLogin";
        }
    }

    @GetMapping("/loadSignup")
    public String getRegPage(@ModelAttribute("user") User user){
        return "signup";
    }
    @PostMapping("/signup")
    public String saveUser(@ModelAttribute("user") User user, Model model){
        userService.save(user);
        return "redirect:/User/loadLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/User/loadLogin";
    }

}
