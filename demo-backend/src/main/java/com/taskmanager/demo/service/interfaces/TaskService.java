package com.taskmanager.demo.service.interfaces;

import com.taskmanager.demo.presentation.dto.request.SaveTask;
import com.taskmanager.demo.presentation.dto.request.UpdateTask;
import com.taskmanager.demo.presentation.dto.response.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAll();
    TaskDto createTask(SaveTask saveTask);
    TaskDto updateTask(UpdateTask updateTask, Long id);
    TaskDto deleteTask(Long id);
}
