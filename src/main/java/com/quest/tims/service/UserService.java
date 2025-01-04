package com.quest.tims.service;

import com.quest.tims.entity.User;
import com.quest.tims.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Retrieve all users
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users); // HTTP 200 with list of users
    }

    // Retrieve a user by ID
    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // HTTP 200 with user details
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // HTTP 404 with null body
        }
    }

    // Add a new user
    public ResponseEntity<User> createUser(User user) {
        // Ensuring the username is unique
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null); // HTTP 409 for conflict
        }
        User createdUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdUser); // HTTP 201 with created user
    }

    // Delete a user by ID
    public ResponseEntity<String> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " does not exist"); // HTTP 404
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully."); // HTTP 200
    }
}
