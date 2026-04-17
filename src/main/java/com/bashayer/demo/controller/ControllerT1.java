package com.bashayer.demo.controller;
import com.bashayer.demo.model.User;
import com.bashayer.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.*;
import java.util.List;
@RestController
//@RequestMapping("/api")
public class ControllerT1 {
  @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @PostMapping("/api/add")
    public ResponseEntity<User>addUsers(@Valid @RequestBody User user) {
        User SavedUser = userRepository.save(user);
        return ResponseEntity.ok(SavedUser);
    }
    @GetMapping("/api/all")
    public List<User> allUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        java.util.Optional<User>user=userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        else  {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id,
                                           @Valid @RequestBody User userDetails) {
        return userRepository.findById(id).map(user ->  {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());

            User updateUser = userRepository.save(user);
            return ResponseEntity.ok(updateUser);
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).map(user ->
        {
            userRepository.delete(user);
            return ResponseEntity.noContent().<User>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
