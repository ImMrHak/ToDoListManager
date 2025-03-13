package com.tdlm.domain.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdlm.domain.task.model.Task;
import com.tdlm.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDo {
    @Id
    @UuidGenerator
    private UUID toDoId;

    private String title;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "toDo", cascade = CascadeType.ALL)
    private List<Task> listOfTask = new ArrayList<>();
}
