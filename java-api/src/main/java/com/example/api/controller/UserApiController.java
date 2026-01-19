package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        User loggedInUser = userService.login(user.getEmail(), user.getPassword());
        if (loggedInUser == null) {
            return "Invalid Email or Password!";
        }
        return loggedInUser;
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome to Home API ✅";
    }

    @GetMapping("/help")
    public String help() {
        return "Help API: Contact admin@example.com";
    }
}
