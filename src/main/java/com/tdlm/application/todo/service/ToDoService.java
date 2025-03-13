package com.tdlm.application.todo.service;

import com.tdlm.application.todo.record.request.GetMyToDoListDTO;
import com.tdlm.application.todo.record.response.ToDoPagedResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ToDoService {
    ToDoPagedResponseDTO getAllMyToDoList(GetMyToDoListDTO getMyToDoListDTO);
}
