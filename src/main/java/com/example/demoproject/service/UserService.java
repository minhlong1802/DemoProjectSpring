package com.example.demoproject.service;

import com.example.demoproject.dto.request.UserCreationRequest;
import com.example.demoproject.entity.User;
import com.example.demoproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreationRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setUserId(request.getUserId());
        user.setLdap(request.getLdap());
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
