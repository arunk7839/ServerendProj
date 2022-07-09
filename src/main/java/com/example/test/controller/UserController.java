package com.example.test.controller;

import com.example.test.model.Role;
import com.example.test.model.User;
import com.example.test.model.UserRole;
import com.example.test.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User createUser(@RequestBody User user)  {

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(this.bCryptPasswordEncoder.encode(user.getConfirmPassword()));

        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(44L);
        role.setRoleName("NORMAL");
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);

        return this.userService.createUser(user, roles);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        return this.userService.getAllUser();
    }

}
