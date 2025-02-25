package com.example.homework_platform.reports.services;

import com.example.homework_platform.reports.models.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper за преобразуване на ResultSet в обект Report
    private final RowMapper<Report> reportRowMapper = (rs, rowNum) -> {
        Report report = new Report();
        report.setId(rs.getInt("id"));
        report.setAuthorId(rs.getInt("author_id"));
        report.setReportType(rs.getString("report_type"));
        report.setGeneratedAt(rs.getTimestamp("generated_at"));
        report.setReportData(rs.getString("report_data"));
        return report;
    };

    // Взимане на отчет по ID
    public Report findById(int id) {
        String sql = "SELECT * FROM Reports WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, reportRowMapper, id);
    }

    // Взимане на всички отчети за даден автор
    public List<Report> findByAuthorId(int authorId) {
        String sql = "SELECT * FROM Reports WHERE author_id = ?";
        return jdbcTemplate.query(sql, reportRowMapper, authorId);
    }

    // Генериране на нов отчет
    public void save(Report report) {
        String sql = "INSERT INTO Reports (author_id, report_type, report_data) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, report.getAuthorId(), report.getReportType(), report.getReportData());
    }

    // Взимане на всички отчети
    public List<Report> findAll() {
        String sql = "SELECT * FROM Reports";
        return jdbcTemplate.query(sql, reportRowMapper);
    }
}