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


@Controller
@SessionAttributes("username")
@RequestMapping("/User")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
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
        User existingUser = userService.findById(user.getIdUser());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getPassword());
            return userService.save(existingUser);
        }
        else{
            return null;
        }
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
            session.setAttribute("username", email);
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
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/User/loadLogin";
    }

}
