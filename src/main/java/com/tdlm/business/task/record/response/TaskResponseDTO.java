package com.tdlm.business.task.record.response;

import com.tdlm.domain.task.enumeration.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDTO(
        UUID taskId,

        String taskName,

        Boolean completed,

        TaskStatus taskStatus,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
