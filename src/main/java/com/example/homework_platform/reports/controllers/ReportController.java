package com.example.homework_platform.reports.controllers;

import com.example.homework_platform.reports.models.Report;
import com.example.homework_platform.reports.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Генериране на нов отчет
    @PostMapping("/generate")
    public void generateReport(@RequestBody Report report) {
        reportService.generateReport(report);
    }

    // Взимане на отчет по ID
    @GetMapping("/{id}")
    public Report getReport(@PathVariable int id) {
        return reportService.getReportById(id);
    }

    // Взимане на всички отчети за даден автор
    @GetMapping("/author/{authorId}")
    public List<Report> getReportsByAuthor(@PathVariable int authorId) {
        return reportService.getReportsByAuthor(authorId);
    }

    // Взимане на всички отчети
    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }
}