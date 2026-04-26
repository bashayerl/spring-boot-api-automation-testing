package com.bashayer.demo.service;

import com.bashayer.demo.dto.UserRequest;
import com.bashayer.demo.dto.UserResponse;
import com.bashayer.demo.model.User;
import com.bashayer.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToResponse);
    }

    public UserResponse addUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    public Optional<UserResponse> updateUser(Long id, UserRequest userRequest) {
        return userRepository.findById(id)
                .map(user -> {
                    if (!user.getEmail().equals(userRequest.getEmail())
                            && userRepository.existsByEmail(userRequest.getEmail())) {
                        throw new IllegalArgumentException("Email already exists");
                    }

                    user.setName(userRequest.getName());
                    user.setEmail(userRequest.getEmail());

                    User updatedUser = userRepository.save(user);
                    return mapToResponse(updatedUser);
                });
    }

    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }

    public List<UserResponse> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}