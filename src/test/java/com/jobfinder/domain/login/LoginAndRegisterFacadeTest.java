package com.jobfinder.domain.login;

import com.jobfinder.domain.login.dto.RegistrationResultDto;
import com.jobfinder.domain.login.dto.UserDto;
import com.jobfinder.domain.login.dto.UserRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAndRegisterFacadeTest {

    LoginAndRegisterFacade loginAndRegisterFacade = new LoginAndRegisterFacade(new InMemoryUserRepository());

    @Test
    void should_register_user(){
        // given
        UserRequestDto userFromRequest = UserRequestDto.builder().username("User1").password("Password1").build();
        // when
        RegistrationResultDto registrationResultDto = loginAndRegisterFacade.registerUser("User1", "Password1");
        // then
        assertAll(
                () -> assertEquals(registrationResultDto.username(), userFromRequest.username()),
                () -> assertEquals(registrationResultDto.password(), userFromRequest.password())
        );
    }

    @Test
    void should_find_user_by_username() {
        // given
        UserRequestDto userRequestDto = UserRequestDto.builder().username("username").password("password").build();
        String encodedPassword = "encodedPassword";
        RegistrationResultDto registrationResultDto = loginAndRegisterFacade.registerUser(userRequestDto.username(), encodedPassword);
        // when
        UserDto byUsername = loginAndRegisterFacade.findByUsername(registrationResultDto.username());

        // then
        assertEquals(new UserDto(registrationResultDto.id(),userRequestDto.username(),userRequestDto.password()), byUsername);
    }

    @Test
    void should_throw_exception_when_user_not_found(){
        // given
        String username = "nonExistentUser";
        // when
        Throwable thrown = catchThrowable(() -> loginAndRegisterFacade.findByUsername(username));
        // then
        assertInstanceOf(UsernameNotFoundException.class, thrown);
        assertEquals("User with username: " + username + " not found", thrown.getMessage());
    }

}