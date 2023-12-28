package com.example.Project.Controller;

import com.example.Project.Model.User;
import com.example.Project.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;


    public LoginController(UserDao userDao){
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

            return "main";
        } else {

            model.addAttribute("message", "Invalid username or password");
            return "login";
        }
    }
}