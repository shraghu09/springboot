package com.example.webapp.controller;

import com.example.webapp.model.User;
import com.example.webapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        User user = userService.login(email, password);

        if (user != null) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid Email or Password!");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {

        boolean success = userService.register(user);

        if (success) {
            model.addAttribute("msg", "Registered Successfully!");
        } else {
            model.addAttribute("msg", "Email already exists!");
        }

        return "register";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/help")
    public String helpPage() {
        return "help";
    }
}
