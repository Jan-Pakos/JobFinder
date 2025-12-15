package com.jobfinder.domain.login.dto;

import lombok.Builder;

@Builder
public record UserDto(
        Long id,
        String username,
        String password
) {
}
