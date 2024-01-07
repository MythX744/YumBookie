package com.example.Project.Controller;


import com.example.Project.Model.Recipe;
import com.example.Project.Model.User;
import com.example.Project.Service.IRecipeService;
import com.example.Project.Service.IUserService;

import com.example.Project.Model.User;
import com.example.Project.dao.UserDao;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@RequestParam("name") String name,
                             @RequestParam("password") String password, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(!name.isEmpty()){
            user.setName(name);
        }
        if(!password.isEmpty()){
            user.setPassword(password);
        }
        userService.update(user);
        return "redirect:/navigation/loadProfile";
    }

    @GetMapping("/loadUsers")
    public String showUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/User/loadUsers";
    }

    @GetMapping("/loadLogin")
    public String getLoginPage(Model leModel) {
        User theUser = new User();
        leModel.addAttribute("user", theUser);
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        if ((user = userService.validateUser(email, password)) != null) {
            session.setAttribute("user", user);
            //System.out.println(user);
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
