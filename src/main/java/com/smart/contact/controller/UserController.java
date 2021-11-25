package com.smart.contact.controller;

import com.smart.contact.dao.UserRepository;
import com.smart.contact.entities.User;
import com.smart.contact.helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/do-register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               @RequestParam(value= "agreement", defaultValue = "false") boolean agreement,
                               Model model,
                               HttpSession session) {

        try {
            if(!agreement) {
                throw new Exception("You have not agreed terms and conditions");
            }
            if(result.hasErrors()) {
                model.addAttribute("user", user);
                return "signUp";
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.jpeg");
            User savedUser = this.userRepository.save(user);
            System.out.println(savedUser);
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully registered", "alert-success"));
        } catch(Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong : " + e.getMessage(), "alert-danger"));
        }
        return "signUp";
    }

    @RequestMapping("/user/index")
    public String dashboard(Model model, Principal principal) {
        String userName = principal.getName();
        User user = userRepository.getUserByUserName(userName);
        System.out.println("User post login : " + user);
        model.addAttribute("user", user);
        return "normal/user_dashboard";
    }
}
