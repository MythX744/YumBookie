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
public class RegistrationController {
  private UserDao userDao;

    @Autowired
    public RegistrationController(UserDao userDao){
        this.userDao=userDao;
    }

    @GetMapping("/signup")
    public String getRegPage(@ModelAttribute("user") User user){
        return "signup";
    }
    @PostMapping("/signup")
    public String saveuser(@ModelAttribute("user") User user, Model model){
        userDao.save(user);
        model.addAttribute("message", "Submitted successfully");
        return "login";
    }
}
