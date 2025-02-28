package com.taskmanager.demo.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "tarea")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "titulo")
    private String title;

    @Column(nullable = false, name = "descripcion")
    private String description;

    @Column(nullable = false, name = "completada")
    private boolean completed;

    @PrePersist
    public void prePersist() {
        this.completed = false;
    }

    public Task() {
    }

    public Task(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


}
