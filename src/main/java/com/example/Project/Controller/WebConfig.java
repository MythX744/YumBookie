package com.example.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/Recipe/loadAddRecipe",
                "/Recipe/showUserRecipes",
                "/Recipe/showAllRecipes",
                "/navigation/loadProfile",
                "/navigation/home",
                "/navigation/lunch",
                "/navigation/breakfast",
                "/navigation/snack",
                "/navigation/dinner",
                "/navigation/loadAddRecipe",
                "/navigation/loadAllRecipes",
                "/navigation/loadEachRecipe"
                );
    }
}
