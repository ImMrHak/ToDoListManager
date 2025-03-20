package com.tdlm.business.user.record.request;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull
        String username,
        @NotNull
        String password
) {
}
