package com.taskmanager.demo.presentation.advice.handlers;

import com.taskmanager.demo.presentation.advice.model.HttpErrorResponse;
import com.taskmanager.demo.service.exceptions.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleTaskNotFoundException(TaskNotFoundException e) {
        log.error(  e.getMessage());

        HttpErrorResponse response = new HttpErrorResponse(
                null,
                null,
                e.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorResponse> handleException(Exception e) {
        log.error(e.getMessage());
        HttpErrorResponse response = new HttpErrorResponse(
                null,
                null,
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


}
