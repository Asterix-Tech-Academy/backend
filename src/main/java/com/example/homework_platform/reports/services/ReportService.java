package com.example.homework_platform.reports.services;

import com.example.homework_platform.reports.models.Report;

import java.util.List;

public interface ReportService {
    Report getReportById(int id);
    List<Report> getReportsByAuthor(int authorId);
    void generateReport(Report report);
    List<Report> getAllReports();
}