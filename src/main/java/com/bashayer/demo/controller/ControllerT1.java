package com.bashayer.demo.controller;
import org.springframework.http.HttpStatus;
import com.bashayer.demo.model.User;
import com.bashayer.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerT1 {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @PostMapping("/add")
    public ResponseEntity<?> addUsers(@Valid @RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email already exists");
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/all")
    public List<User> allUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/users/search")
    public List<User> searchUsersByName(@RequestParam("name") String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                                        @Valid @RequestBody User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    if (!user.getEmail().equals(userDetails.getEmail())
                            && userRepository.existsByEmail(userDetails.getEmail())) {
                        return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body("Email already exists");
                    }

                    user.setName(userDetails.getName());
                    user.setEmail(userDetails.getEmail());

                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.noContent().<User>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}