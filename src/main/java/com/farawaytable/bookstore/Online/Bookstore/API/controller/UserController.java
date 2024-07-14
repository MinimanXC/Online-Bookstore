package com.farawaytable.bookstore.Online.Bookstore.API.controller;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import com.farawaytable.bookstore.Online.Bookstore.API.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user)
    {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest)
    {
        return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
