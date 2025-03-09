package com.tdlm.application.task.service;

import com.tdlm.domain.task.repository.TaskDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {
    private final TaskDomainRepository taskDomainRepository;
}
