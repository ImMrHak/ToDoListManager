package com.tdlm.business.todo.mapper;

import com.tdlm.business.todo.record.response.ToDoResponseDTO;
import com.tdlm.domain.todo.model.ToDo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    ToDoResponseDTO toToDoResponseDTO(ToDo toDo);
}
