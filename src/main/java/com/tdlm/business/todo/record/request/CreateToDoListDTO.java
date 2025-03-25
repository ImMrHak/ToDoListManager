package com.tdlm.business.todo.record.request;

import jakarta.validation.constraints.NotNull;

public record CreateToDoListDTO(
        @NotNull
        String userName,
        @NotNull
        String title,
        String description
) {
}
