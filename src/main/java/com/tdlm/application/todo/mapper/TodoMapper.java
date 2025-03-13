package com.tdlm.application.todo.mapper;

import com.tdlm.application.todo.record.response.ToDoResponseDTO;
import com.tdlm.domain.todo.model.ToDo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    ToDoResponseDTO toToDoResponseDTO(ToDo toDo);
}
