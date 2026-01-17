package com.jobfinder.domain.login.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(
        String id,
        String username,
        String password
) {
}
