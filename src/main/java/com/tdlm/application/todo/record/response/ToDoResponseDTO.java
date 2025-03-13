package com.tdlm.application.todo.record.response;

import com.tdlm.domain.task.model.Task;
import com.tdlm.domain.user.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
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
