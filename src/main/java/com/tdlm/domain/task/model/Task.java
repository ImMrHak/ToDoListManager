package com.tdlm.domain.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdlm.domain.task.enumeration.TaskStatus;
import com.tdlm.domain.todo.model.ToDo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @UuidGenerator
    private UUID taskId;

    private String taskName;

    private Boolean completed;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne @JsonIgnore
    private ToDo toDo;
}
