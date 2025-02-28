package com.taskmanager.demo.utils.mapper;

import com.taskmanager.demo.persistence.entity.Task;
import com.taskmanager.demo.presentation.dto.request.SaveTask;
import com.taskmanager.demo.presentation.dto.response.TaskDto;

public class TaskMapper {

    public static TaskDto toDto(Task task) {
        return new TaskDto(
                task.isCompleted(),
                task.getTitle(),
                task.getId(),
                task.getDescription()
        );
    }

    public static Task toEntity(SaveTask saveTask) {
        return new Task(
                saveTask.getDescription(),
                saveTask.getTitle()
        );
    }

}
