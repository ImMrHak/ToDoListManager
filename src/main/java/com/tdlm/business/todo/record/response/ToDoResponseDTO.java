package com.tdlm.business.todo.record.response;

import com.tdlm.domain.task.model.Task;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ToDoResponseDTO(
        UUID toDoId,
        String title,
        String description,
        Date createdAt,
        Date updatedAt,
        List<Task> listOfTask
) {
}
