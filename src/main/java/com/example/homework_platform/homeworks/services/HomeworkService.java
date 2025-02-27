package com.example.homeworkplatform.homeworks.services;

import com.example.homeworkplatform.homeworks.models.Homework;
import org.springframework.stereotype.Service;

@Service
public class HomeworkService {
    private final HomeworkRepository repository;

    public HomeworkService(HomeworkRepository repository) {
        this.repository = repository;
    }

    public void createHomework(Homework homework) {
        if (homework.getTitle() == null || homework.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        repository.saveHomework(homework);
    }

    public Homework getHomework(Long id) {
        Homework homework = repository.findHomeworkById(id);
        if (homework == null) {
            throw new RuntimeException("Homework not found with id: " + id);
        }
        return homework;
    }

    public void updateHomework(Long id, Homework updatedHomework) {
        if (updatedHomework.getTitle() == null || updatedHomework.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        int rowsUpdated = repository.updateHomework(id, updatedHomework);
        if (rowsUpdated == 0) {
            throw new RuntimeException("Homework not found with id: " + id);
        }
    }

    public void deleteHomework(Long id) {
        int rowsDeleted = repository.deleteHomework(id);
        if (rowsDeleted == 0) {
            throw new RuntimeException("Homework not found with id: " + id);
        }
    }

}