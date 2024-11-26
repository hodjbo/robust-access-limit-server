package com.hodbenor.project.robust.access.limit.rest;

import com.hodbenor.project.robust.access.limit.UserService;
import com.hodbenor.project.robust.access.limit.data.beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {

        User savedUser = userService.createUser(user);
        if (savedUser == null) {
            LOG.error("Failed to create new user for id: {}", user.getId());
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(String.format("User with id %d created.", savedUser.getId()));
    }

    @GetMapping(path = "/")
    public ResponseEntity<User> getUser(@RequestHeader("user-id") long userId) {

        User user = userService.getUser(userId);
        if (user == null) {
            LOG.error("Failed to get user id: {}", userId);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();
        if (users == null) {
            LOG.error("Failed to get all users");
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(users);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateUserNames(@RequestBody User user) {

        if (userService.updateUserNames(user)) {
            return ResponseEntity.ok("update succeeded");
        }

        LOG.error("Failed to update user names for user {}", user.getId());
        return ResponseEntity.internalServerError().body("Failed to update user names");
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> removeUser(@RequestHeader("user-id") long userId) {
        userService.removeUser(userId);

        return ResponseEntity.ok("user removed");
    }
}