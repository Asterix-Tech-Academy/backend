package com.example.homework_platform.Users.controller;

import com.example.homework_platform.Users.entity.Users;
import com.example.homework_platform.Users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService userService) {
        this.usersService = userService;
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable int id) {
        Optional<Users> user = usersService.getUserById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public void createUser(@RequestBody Users user) {
        usersService.createUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody Users user) {
        user.setId(id);
        usersService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        usersService.deleteUserById(id);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }
}
