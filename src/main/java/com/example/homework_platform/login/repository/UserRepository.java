package com.example.homework_platform.login.repository;

import com.example.homework_platform.login.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("loginUserRepository")
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password_hash"),
            rs.getString("role")
    );

    public List<User> findAll() { //select users
        return jdbcTemplate.query("SELECT * FROM Users", userRowMapper);
    }

    public Optional<User> findByUsername(String username) {
        List<User> users = jdbcTemplate.query("SELECT * FROM Users WHERE username = ?", userRowMapper, username);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO Users (username, email, password_hash, role) VALUES (?, ?, ?, ?)",
                user.getUsername(), user.getEmail(), user.getPasswordHash(), user.getRole());
    }
}

