package com.example.webapp.service;

import com.example.webapp.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public String register(User user) {
        if (users.containsKey(user.getEmail())) {
            return "User already exists!";
        }
        users.put(user.getEmail(), user);
        return "Registered Successfully!";
    }

    public User login(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
