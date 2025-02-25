package com.example.homework_platform.reports.models;

import java.sql.Timestamp;

public class Report {
    private int id;
    private int authorId;
    private String reportType;
    private Timestamp generatedAt;
    private String reportData;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public Timestamp getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(Timestamp generatedAt) { this.generatedAt = generatedAt; }
    public String getReportData() { return reportData; }
    public void setReportData(String reportData) { this.reportData = reportData; }
}