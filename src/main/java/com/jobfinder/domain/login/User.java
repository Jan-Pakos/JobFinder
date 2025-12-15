package com.jobfinder.domain.login;

import lombok.Builder;

@Builder
record User(
        Long id,
        String username,
        String password
) { }

