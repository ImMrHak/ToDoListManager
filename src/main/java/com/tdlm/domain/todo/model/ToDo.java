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

    @ManyToOne
    private User owner;

    @ManyToMany(mappedBy = "listOfCollabToDo", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<User> collaborators = new ArrayList<>();

    @OneToMany(mappedBy = "toDo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Task> listOfTask = new ArrayList<>();
}
