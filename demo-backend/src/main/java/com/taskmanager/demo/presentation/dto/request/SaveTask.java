package com.taskmanager.demo.presentation.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveTask {

    @NotNull(message = "El título no puede ser nulo")
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotNull(message = "La descripción no puede ser nula")
    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    public SaveTask(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
