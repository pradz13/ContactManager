package com.smart.contact.controller;

import com.smart.contact.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @PostMapping("/do-register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam(value= "agreement", defaultValue = "false") boolean agreement) {

        System.out.println(user);
        System.out.println(agreement);
        return "signUp";
    }
}
