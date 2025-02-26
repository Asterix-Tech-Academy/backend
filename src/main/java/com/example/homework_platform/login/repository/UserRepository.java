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
            rs.getString("role"),
            rs.getString("status")
    );

    public List<User> findAll() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, username);
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }

    public void delete(String username) {
        String sql = "UPDATE Users SET status = 'DELETED' WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }
}
