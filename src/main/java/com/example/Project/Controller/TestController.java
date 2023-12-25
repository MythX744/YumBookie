package com.example.Project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Thymeleaf is working!");
        return "login";
    }
}