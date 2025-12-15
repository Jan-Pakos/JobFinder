package com.jobfinder.domain.login.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(
        Long id,
        String username,
        String password
) {
}
