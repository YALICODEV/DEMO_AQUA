package com.taskmanager.demo.service.impl;

import com.taskmanager.demo.persistence.entity.Task;
import com.taskmanager.demo.persistence.repository.TaskRepository;
import com.taskmanager.demo.presentation.dto.request.SaveTask;
import com.taskmanager.demo.presentation.dto.request.UpdateTask;
import com.taskmanager.demo.presentation.dto.response.TaskDto;
import com.taskmanager.demo.service.exceptions.TaskNotFoundException;
import com.taskmanager.demo.service.interfaces.TaskService;
import com.taskmanager.demo.utils.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto createTask(SaveTask saveTask) {
        Task task = TaskMapper.toEntity(saveTask);
        taskRepository.save(task);

        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDto updateTask(UpdateTask updateTask, Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarea con id " + id + " no encontrada"));

        task.setCompleted(updateTask.isCompleted());
        taskRepository.save(task);

        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDto deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarea con id " + id + " no encontrada"));

        taskRepository.delete(task);

        return TaskMapper.toDto(task);
    }
}
