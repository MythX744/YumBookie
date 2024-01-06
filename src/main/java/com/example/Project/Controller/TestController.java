package com.example.Project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Thymeleaf is working!");
        return "redirect:/User/loadLogin";
    }
}