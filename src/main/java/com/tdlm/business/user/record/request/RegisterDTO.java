package com.tdlm.business.user.record.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @Email
        String email,

        @NotNull
        String username,

        @NotNull
        String password,

        @NotNull
        String confirmPassword,

        @NotNull
        String firstName,

        @NotNull
        String lastName
) {
}
