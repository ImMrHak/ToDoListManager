package com.tdlm.kernel.exception.task;

public class TaskAlreadyExistException extends RuntimeException {
    public TaskAlreadyExistException(String message) { super(message); }
}
