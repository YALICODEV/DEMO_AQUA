package com.taskmanager.demo.presentation.advice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class HttpErrorResponse {
    private String message;
    private int status;
    private Map<String, String> errors;

    @JsonProperty("general_errors")
    private List<String> generalErrors;

    public HttpErrorResponse(Map<String, String> errors, List<String> generalErrors, String message, int status) {
        this.errors = errors;
        this.generalErrors = generalErrors;
        this.message = message;
        this.status = status;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public List<String> getGeneralErrors() {
        return generalErrors;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
