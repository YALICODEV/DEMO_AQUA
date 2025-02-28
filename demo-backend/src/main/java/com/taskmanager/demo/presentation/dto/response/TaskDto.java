package com.taskmanager.demo.presentation.dto.response;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;

    public Long getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public TaskDto(boolean completed, String description, Long id, String title) {
        this.completed = completed;
        this.description = description;
        this.id = id;
        this.title = title;
    }
}
