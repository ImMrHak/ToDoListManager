package com.tdlm.business.user.record.response;

public record TokenDTO(
        String accessToken,
        String refreshToken
) {
}
