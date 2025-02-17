package com.example.homework_platform.registration.controller;

import com.example.homework_platform.registration.dto.RegisterRequest;
import com.example.homework_platform.registration.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        String message = userService.register(request);

        if (message.equals("User registered successfully!")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.badRequest().body(message);
        }
    }
}

