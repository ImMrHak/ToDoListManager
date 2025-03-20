package com.tdlm.business.todo.record.request;

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
