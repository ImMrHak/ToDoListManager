package com.tdlm.kernel.exception.todo;

public class ToDoNotFoundException extends RuntimeException{
    public ToDoNotFoundException(String message) {
        super(message);
    }
}
