package com.example.demo3.service;

import com.example.demo3.entity.User;
import com.example.demo3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> saveUsers(List<User> users) {
        for (User user : users) {
            if (userRepository.existsById(user.getId())) {
                throw new IllegalArgumentException("ID " + user.getId() + " already exists");
            }
            if (user.getId() == null) {
                throw new IllegalArgumentException("ID must not be null");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.saveAll(users);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
