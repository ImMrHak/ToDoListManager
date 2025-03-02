package com.tdlm.domain.task.model;

import com.tdlm.domain.listener.AuditListener;
import com.tdlm.domain.listener.Auditable;
import com.tdlm.domain.task.enumeration.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListener.class)
@Builder
public class Task implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    private String taskName;

    private Boolean completed;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    private Date createdAt;

    private Date updatedAt;
}
