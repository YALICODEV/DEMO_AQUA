package com.taskmanager.demo.presentation.controller;

import com.taskmanager.demo.presentation.dto.request.SaveTask;
import com.taskmanager.demo.presentation.dto.request.UpdateTask;
import com.taskmanager.demo.presentation.dto.response.TaskDto;
import com.taskmanager.demo.service.interfaces.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        log.info("TaskController::getAllTasks");
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping
    public ResponseEntity<TaskDto> saveOneTask(@Valid @RequestBody SaveTask saveTask) {
        log.info("TaskController::saveOneTask - body: {}", saveTask);
        return ResponseEntity.ok(taskService.createTask(saveTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateOneTask(
            @RequestBody UpdateTask updateTask,
            @PathVariable("id") Long id) {
        log.info("TaskController::updateOneTask - id: {}, body: {} " ,id, updateTask);
        return ResponseEntity.ok(taskService.updateTask(updateTask, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> deleteOneTask(
            @PathVariable("id") Long id
    ) {
        log.info("TaskController::deleteOneTask - id: {}", id);
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

}
