package com.example.homework_platform.registration.service;

import com.example.homework_platform.registration.dto.RegisterRequest;
import com.example.homework_platform.registration.model.User;
import com.example.homework_platform.registration.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(@Valid UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email)) {
            return "Email is already in use!";
        }

        User user = new User();
        user.setFirstName(request.firstName);
        user.setLastName(request.lastName);
        user.setEmail(request.email);
        user.setUsername(request.username);
        user.setPasswordHash(passwordEncoder.encode(request.password));
        user.setRole(request.role);
        user.setPhoneNumber(request.phoneNumber);
        user.setAddress(request.address);
        user.setSubject(request.subject);
        user.setIsClassTeacher(request.isClassTeacher);
        user.setQualification(request.qualification);
        user.setClassName(request.className);

        userRepository.save(user);
        return "User registered successfully!";
    }
}
