package com.taskmanager.demo.presentation.dto.request;

public class UpdateTask {

    private boolean completed;

    public UpdateTask() {
    }

    public UpdateTask(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return completed;
    }
}
