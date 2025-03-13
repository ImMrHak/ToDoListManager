package com.tdlm.application.todo.record.request;

import jakarta.validation.constraints.NotNull;

public record GetMyToDoListDTO(
        @NotNull
        String userName,
        @NotNull
        Integer offset,
        @NotNull
        Integer pageSize
) {
}
