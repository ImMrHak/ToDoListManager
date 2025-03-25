package com.tdlm.business.todo.service;

import com.tdlm.business.todo.record.request.CreateToDoListDTO;
import com.tdlm.business.todo.record.request.GetMyToDoListDTO;
import com.tdlm.business.todo.record.response.ToDoPagedResponseDTO;
import com.tdlm.business.todo.record.response.ToDoResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ToDoService {
    ToDoPagedResponseDTO getAllMyToDoList(GetMyToDoListDTO getMyToDoListDTO);
    ToDoResponseDTO createMyToDoList(CreateToDoListDTO createToDoListDTO);
}
