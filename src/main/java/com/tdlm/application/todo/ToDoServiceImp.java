package com.tdlm.application.todo;

import com.tdlm.domain.todo.repository.ToDoDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoServiceImp implements ToDoService {
    private final ToDoDomainRepository toDoDomainRepository;
}
