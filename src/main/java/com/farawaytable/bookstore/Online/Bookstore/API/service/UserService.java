package com.farawaytable.bookstore.Online.Bookstore.API.service;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import com.farawaytable.bookstore.Online.Bookstore.API.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user)
    {
        if (userAlreadyExists(user.getUsername())) { return null; }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.USER);
        return userRepository.save(user);
    }

    public String loginUser(String username, String password) {
        if (!userAlreadyExists(username)) { return "User not found"; }

        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isEmpty()) { return "User not found"; }

        if (passwordEncoder.matches(password, existingUser.get().getPassword()))
        {
            //return "dummy_token";
            return "User successfully logged in";
        }

        return "Invalid credentials";
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    private boolean userAlreadyExists(String username)
    {
        return userRepository.findByUsername(username).isPresent();
    }
}
