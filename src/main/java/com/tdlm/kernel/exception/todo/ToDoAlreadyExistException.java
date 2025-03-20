package com.tdlm.kernel.exception.todo;

public class ToDoAlreadyExistException extends RuntimeException{
    public ToDoAlreadyExistException(String message) {
        super(message);
    }
}
