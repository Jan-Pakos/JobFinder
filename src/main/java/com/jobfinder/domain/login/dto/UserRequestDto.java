package com.jobfinder.domain.login.dto;

import lombok.Builder;

@Builder
public record UserRequestDto(
        String username,
        String password
) {
}
