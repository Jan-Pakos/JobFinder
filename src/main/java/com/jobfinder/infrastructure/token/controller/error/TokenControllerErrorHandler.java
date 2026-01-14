package com.jobfinder.infrastructure.token.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TokenControllerErrorHandler {

    private static final String BAD_CREDENTIALS_MESSAGE = "Bad Credentials";
    private static final String USER_NOT_FOUND = "User not found";

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public TokenErrorResponseDto handleBadCredentials() {
        return TokenErrorResponseDto.builder()
                .message(BAD_CREDENTIALS_MESSAGE)
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }


}
