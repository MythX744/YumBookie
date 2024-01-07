package com.example.Project.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/navigation")
public class PageController {

    @GetMapping("/home")
    public String LoadHome(Model theModel){
        return "home";
    }

    @GetMapping("/lunch")
    public String LoadLunch(Model theModel){
        return "";
    }

    @GetMapping("/breakfast")
    public String LoadBreakfast(Model theModel){
        return "";
    }

    @GetMapping("/snack")
    public String LoadSnake(Model theModel){
        return "";
    }

    @GetMapping("/dinner")
    public String LoadDinner(Model theModel){
        return "";
    }

    @GetMapping("/User/loadProfile")
    public String LoadProfile(Model theModel){
        return "profile";
    }



}
