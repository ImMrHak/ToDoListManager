package com.tdlm.application.user.record.response;

public record TokenDTO(
        String accessToken,
        String refreshToken
) {
}
