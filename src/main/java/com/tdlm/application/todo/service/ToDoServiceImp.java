package com.tdlm.application.todo.service;

import com.tdlm.domain.todo.repository.ToDoDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToDoServiceImp implements ToDoService {
    private final ToDoDomainRepository toDoDomainRepository;
}
