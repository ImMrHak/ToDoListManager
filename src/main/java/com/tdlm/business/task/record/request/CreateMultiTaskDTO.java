package com.tdlm.business.task.record.request;

import com.tdlm.domain.task.enumeration.TaskStatus;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record CreateMultiTaskDTO(
        @NotNull
        UUID toDoId,

        @NotNull
        Map<String, TaskStatus> taskNameStatus
) {
}
