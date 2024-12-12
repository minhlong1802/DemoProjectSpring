package com.example.demoproject.controller;

import com.example.demoproject.dto.request.UserCreationRequest;
import com.example.demoproject.entity.User;
import com.example.demoproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detail/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
}
