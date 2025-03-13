package com.tdlm.application.todo.service;

import com.tdlm.application.todo.mapper.TodoMapper;
import com.tdlm.application.todo.record.request.GetMyToDoListDTO;
import com.tdlm.application.todo.record.response.ToDoPagedResponseDTO;
import com.tdlm.application.todo.record.response.ToDoResponseDTO;
import com.tdlm.domain.todo.model.ToDo;
import com.tdlm.domain.todo.repository.ToDoDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ToDoServiceImp implements ToDoService {
    private final ToDoDomainRepository toDoDomainRepository;
    private final TodoMapper todoMapper;

    @Override
    //@Cacheable(cacheNames = "allMyToDo", key = "#getMyToDoListDTO.userName() + ':' + #getMyToDoListDTO.offset() + ':' + #getMyToDoListDTO.pageSize()")
    public ToDoPagedResponseDTO getAllMyToDoList(GetMyToDoListDTO getMyToDoListDTO) {
        Page<ToDo> pageToDos = toDoDomainRepository.findAllByUser_Username(getMyToDoListDTO.userName(),
                PageRequest.of(getMyToDoListDTO.offset(), getMyToDoListDTO.pageSize()));

        List<ToDoResponseDTO> toDosResponseDTO = pageToDos.getContent().stream().map(todoMapper::toToDoResponseDTO).toList();

        return new ToDoPagedResponseDTO(toDosResponseDTO, pageToDos.getTotalPages(), pageToDos.getTotalElements(), getMyToDoListDTO.offset() + 1, getMyToDoListDTO.pageSize());
    }
}
