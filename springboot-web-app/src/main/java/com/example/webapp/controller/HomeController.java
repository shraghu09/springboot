package com.example.webapp.controller;

import com.example.webapp.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (!isLoggedIn(session)) return "redirect:/login";

        User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("username", user.getName());
        return "home";
    }

    @GetMapping("/help")
    public String help(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "help";
    }
}
