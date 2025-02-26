package com.example.homework_platform.login.controller;

import com.example.homework_platform.login.dto.LoginRequest;
import com.example.homework_platform.login.dto.UserDTO;
import com.example.homework_platform.login.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<UserDTO> userDTO = loginService.login(loginRequest.getIdentifier(), loginRequest.getPassword());

            if (userDTO.isPresent()) {
                return ResponseEntity.ok(userDTO.get());
            } else {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Internal server error"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }
}
