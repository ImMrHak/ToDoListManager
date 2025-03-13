package com.tdlm.core.exception.todo;

public class ToDoNotFoundException extends RuntimeException{
    public ToDoNotFoundException(String message) {
        super(message);
    }
}
