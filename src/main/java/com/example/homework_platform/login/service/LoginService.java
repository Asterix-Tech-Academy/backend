package com.example.homework_platform.login.service;

import com.example.homework_platform.login.dto.UserDTO;
import com.example.homework_platform.login.entity.User;
import com.example.homework_platform.login.repository.LoginRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService(LoginRepository loginRepository, BCryptPasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDTO> login(String identifier, String password, String role) {
        User user = loginRepository.findUserByIdentifier(identifier)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or email"));

        if ("pending".equalsIgnoreCase(user.getStatus())) {
            throw new IllegalStateException("Your account is pending approval.");
        }
        if ("deleted".equalsIgnoreCase(user.getStatus())) {
            throw new IllegalStateException("Your account has been deleted.");
        }

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid username or email");
        }

        if (role != null && !role.equalsIgnoreCase(user.getRole())) {
            throw new IllegalStateException("Role does not match.");
        }

        return Optional.of(convertToDTO(user));
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getStatus());
    }
}
