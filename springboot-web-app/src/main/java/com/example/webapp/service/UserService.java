package com.example.webapp.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.webapp.model.User;
import com.example.webapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(User user) {
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public User login(String email, String password) {
        Optional<User> existing = userRepository.findByEmail(email);

        if (existing.isPresent() && existing.get().getPassword().equals(password)) {
            return existing.get();
        }
        return null;
    }
}
