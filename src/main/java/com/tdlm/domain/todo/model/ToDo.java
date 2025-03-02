package com.tdlm.domain.todo.model;

import com.tdlm.domain.listener.AuditListener;
import com.tdlm.domain.listener.Auditable;
import com.tdlm.domain.task.model.Task;
import com.tdlm.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListener.class)
@Builder
public class ToDo implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long toDoId;

    private String title;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "toDo")
    private ArrayList<Task> listOfTask;
}
