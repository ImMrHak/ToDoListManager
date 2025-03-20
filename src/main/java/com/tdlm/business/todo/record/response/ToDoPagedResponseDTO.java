package com.tdlm.business.todo.record.response;

import java.util.List;

public record ToDoPagedResponseDTO(
        List<ToDoResponseDTO> toDoResponseDTOS,
        Integer totalPages,
        Long totalElements,
        Integer currentPage,
        Integer pageSize
) {
}
