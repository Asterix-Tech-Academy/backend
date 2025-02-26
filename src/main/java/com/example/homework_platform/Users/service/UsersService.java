package com.example.homework_platform.Users.service;

import com.example.homework_platform.Users.entity.Users;
import com.example.homework_platform.Users.repository.UsersRepository;
import com.example.homework_platform.Users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<Users> getUserById(int id) {
        return usersRepository.findById(id);
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public void createUser(Users user) {
        usersRepository.save(user);
    }

    public void updateUser(Users user) {
        usersRepository.update(user);
    }

    public void deleteUserById(int id) {
        usersRepository.deleteById(id);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

}


