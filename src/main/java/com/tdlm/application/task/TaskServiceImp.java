package com.tdlm.application.task;

import com.tdlm.domain.task.repository.TaskDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {
    private final TaskDomainRepository taskDomainRepository;
}
