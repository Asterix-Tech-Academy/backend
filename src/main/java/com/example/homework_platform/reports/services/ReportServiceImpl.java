package com.example.homework_platform.reports.services;

import com.example.homework_platform.reports.models.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report getReportById(int id) {
        return reportRepository.findById(id);
    }

    @Override
    public List<Report> getReportsByAuthor(int authorId) {
        return reportRepository.findByAuthorId(authorId);
    }

    @Override
    public void generateReport(Report report) {
        reportRepository.save(report);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}