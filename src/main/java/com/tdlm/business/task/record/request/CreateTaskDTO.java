package com.tdlm.business.task.record.request;

import com.tdlm.domain.task.enumeration.TaskStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTaskDTO(
        @NotNull
        UUID toDoId,

        @NotNull
        String taskName,

        @NotNull
        TaskStatus taskStatus
) {
}
