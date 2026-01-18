package com.jobfinder.domain.login.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String id,
        String username,
        String password
) {
}
