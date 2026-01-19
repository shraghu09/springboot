package com.example.webapp.controller;

import com.example.webapp.model.User;
import com.example.webapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        User user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error", "Invalid Email or Password!");
            return "login";
        }

        session.setAttribute("loggedInUser", user);
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password,
                             Model model) {
        String msg = userService.register(new User(name, email, password));
        model.addAttribute("message", msg);
        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
