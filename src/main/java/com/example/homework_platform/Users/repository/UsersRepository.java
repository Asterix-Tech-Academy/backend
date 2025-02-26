package com.example.homework_platform.Users.repository;

import com.example.homework_platform.Users.entity.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Users> findById(int id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        Users user = jdbcTemplate.queryForObject(sql, new Object[]{id}, userRowMapper());
        return Optional.ofNullable(user);
    }


    public Optional<Users> findByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        Users user = jdbcTemplate.queryForObject(sql, new Object[]{email}, userRowMapper());
        return Optional.ofNullable(user);
    }

    public void save(Users user) {
        String sql = "INSERT INTO Users (username, first_name, last_name, email, password_hash, role, created_at, phone_number, address, subject, is_class_teacher, qualification, class_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPasswordHash(), user.getRole(), user.getCreatedAt(), user.getPhoneNumber(),
                user.getAddress(), user.getSubject(), user.isClassTeacher(), user.getQualification(), user.getClassId());
    }

    public void update(Users user) {
        String sql = "UPDATE Users SET username = ?, first_name = ?, last_name = ?, email = ?, password_hash = ?, role = ?, phone_number = ?, address = ?, subject = ?, is_class_teacher = ?, qualification = ?, class_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPasswordHash(), user.getRole(), user.getPhoneNumber(), user.getAddress(), user.getSubject(),
                user.isClassTeacher(), user.getQualification(), user.getClassId(), user.getId());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM Users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Users> findAll() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    private RowMapper<Users> userRowMapper() {
        return (rs, rowNum) -> {
            Users user = new Users();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPasswordHash(rs.getString("password_hash"));
            user.setRole(rs.getString("role"));
            user.setCreatedAt(rs.getTimestamp("created_at"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setAddress(rs.getString("address"));
            user.setSubject(rs.getString("subject"));
            user.setClassTeacher(rs.getBoolean("is_class_teacher"));
            user.setQualification(rs.getString("qualification"));
            user.setClassId(rs.getString("class_id"));
            return user;
        };
    }
}

