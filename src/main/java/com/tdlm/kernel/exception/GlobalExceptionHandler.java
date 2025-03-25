package com.tdlm.kernel.exception;

import com.tdlm.kernel.exception.task.TaskAlreadyExistException;
import com.tdlm.kernel.exception.todo.ToDoAlreadyExistException;
import com.tdlm.kernel.exception.user.UserAlreadyExistException;
import com.tdlm.kernel.exception.user.UserNotFoundException;
import com.tdlm.adapter.rest.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException e) {
        Map<String, Object> response = ResponseWrapper.error("User not found", HttpStatus.NOT_FOUND);
        // ADDING LOGS HERE FOR THE DATABASE !
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> userAlreadyExistException(UserAlreadyExistException e) {
        Map<String, Object> response = ResponseWrapper.error("User already exist", HttpStatus.CONFLICT);
        // ADDING LOGS HERE FOR THE DATABASE !
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }





    @ExceptionHandler(ToDoAlreadyExistException.class)
    public ResponseEntity<Object> toDoAlreadyExistException(ToDoAlreadyExistException e) {
        Map<String, Object> response = ResponseWrapper.error("ToDo already exist", HttpStatus.CONFLICT);
        // ADDING LOGS HERE FOR THE DATABASE !
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TaskAlreadyExistException.class)
    public ResponseEntity<Object> taskAlreadyExistException(TaskAlreadyExistException e) {
        Map<String, Object> response = ResponseWrapper.error("Task name already exist", HttpStatus.CONFLICT);
        // ADDING LOGS HERE FOR THE DATABASE !
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }


}
