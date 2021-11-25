package com.smart.contact.controller;

import com.smart.contact.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About - Contact Manager");
        return "about";
    }

    @RequestMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("title", "Register - Contact Manager");
        model.addAttribute("user", new User());
        return "signUp";
    }

    @RequestMapping("/signin")
    public String customLogin(Model model) {
        model.addAttribute("title", "Login - Contact Manager");
        return "login";
    }
}
