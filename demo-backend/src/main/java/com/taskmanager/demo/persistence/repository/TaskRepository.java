package com.taskmanager.demo.persistence.repository;

import com.taskmanager.demo.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
