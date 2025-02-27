package com.example.homework_platform.login.repository;

import com.example.homework_platform.login.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Repository
public class LoginRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getString("status"));
        return user;
    };

    public Optional<User> findUserByIdentifier(String identifier) {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, identifier, identifier);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void deleteUserByIdentifier(String identifier) {
        String sql = "DELETE FROM users WHERE username = ? OR email = ?";
        jdbcTemplate.update(sql, identifier, identifier);
    }
}
