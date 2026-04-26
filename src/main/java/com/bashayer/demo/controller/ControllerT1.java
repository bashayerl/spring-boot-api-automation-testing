package com.bashayer.demo.controller;

import com.bashayer.demo.dto.UserRequest;
import com.bashayer.demo.dto.UserResponse;
import com.bashayer.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "User Management", description = "APIs for managing users")
public class ControllerT1 {

    private final UserService userService;

    public ControllerT1(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Add a new user", description = "Creates a new user after validating name, email, and duplicate email")
    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUsers(@Valid @RequestBody UserRequest userRequest) {
        UserResponse savedUser = userService.addUser(userRequest);
        return ResponseEntity.ok(savedUser);
    }

    @Operation(summary = "Get all users alternative endpoint", description = "Returns all users using /api/all endpoint")
    @GetMapping("/all")
    public List<UserResponse> allUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Search users by name", description = "Searches users whose names contain the given value")
    @GetMapping("/users/search")
    public List<UserResponse> searchUsersByName(@RequestParam("name") String name) {
        return userService.searchUsersByName(name);
    }

    @Operation(summary = "Get user by ID", description = "Returns a single user by ID")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update user", description = "Updates user name and email by ID")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id,
                                                   @Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete user", description = "Deletes a user by ID")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}