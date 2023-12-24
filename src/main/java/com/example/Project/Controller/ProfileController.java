package com.example.Project.Controller;

import com.example.Project.Model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ProfileController {

    @RequestMapping("/profile")
    public String showFrom(Model model){

        return "profile";
    }


}
