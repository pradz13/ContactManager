package com.smart.contact.controller;

import com.smart.contact.dao.UserRepository;
import com.smart.contact.entities.User;
import com.smart.contact.helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/do-register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam(value= "agreement", defaultValue = "false") boolean agreement,
                               Model model,
                               HttpSession session) {

        try {
            if(!agreement) {
                throw new Exception("You have not agreed terms and conditions");
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.jpeg");
            User result = this.userRepository.save(user);
            System.out.println(result);
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully registered", "alert-success"));
        } catch(Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong : " + e.getMessage(), "alert-danger"));
        }
        return "signUp";
    }
}
