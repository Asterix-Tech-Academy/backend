package com.example.homework_platform.homeworks.models;
import java.sql.Timestamp;
import java.util.Date;

public class Assignment {
    private Long id;
    private String title;
    private String description;
    private Timestamp assignedAt;
    private Date deadline;
    private Long teacherId;
    private String classId;
    private Integer groupId;
    private String descriptionFile;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructors
    public Assignment() {}

    public Assignment(Long id, String title, String description, Timestamp assignedAt, Date deadline, Long teacherId,
                      String classId, Integer groupId, String descriptionFile, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignedAt = assignedAt;
        this.deadline = deadline;
        this.teacherId = teacherId;
        this.classId = classId;
        this.groupId = groupId;
        this.descriptionFile = descriptionFile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getAssignedAt() { return assignedAt; }
    public void setAssignedAt(Timestamp assignedAt) { this.assignedAt = assignedAt; }
    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }
    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public Integer getGroupId() { return groupId; }
    public void setGroupId(Integer groupId) { this.groupId = groupId; }
    public String getDescriptionFile() { return descriptionFile; }
    public void setDescriptionFile(String descriptionFile) { this.descriptionFile = descriptionFile; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
