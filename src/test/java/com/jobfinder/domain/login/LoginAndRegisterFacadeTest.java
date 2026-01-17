package com.jobfinder.domain.login;

import com.jobfinder.domain.login.dto.RegistrationResultDto;
import com.jobfinder.domain.login.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class LoginAndRegisterFacadeTest {

    private LoginAndRegisterFacade loginAndRegisterFacade;

    @BeforeEach
    void setUp() {
        loginAndRegisterFacade = new LoginAndRegisterFacade(new InMemoryUserRepository());
    }

    @Test
    void should_register_user() {
        // given
        String username = "User1";
        String password = "Password1";

        // when
        RegistrationResultDto result = loginAndRegisterFacade.registerUser(username, password);

        // then
        assertThat(result.username()).isEqualTo(username);
    }

    @Test
    void should_throw_exception_when_registering_duplicate_username() {
        // given
        loginAndRegisterFacade.registerUser("User1", "pass");

        // when
        Throwable thrown = catchThrowable(() -> loginAndRegisterFacade.registerUser("User1", "otherPass"));

        // then
        assertThat(thrown)
                .isInstanceOf(UsernameAlreadyExistsException.class)
                .hasMessage("Username: User1 already exists");
    }

    @Test
    void should_find_user_by_username() {
        // given
        String username = "username";
        String password = "password";
        loginAndRegisterFacade.registerUser(username, password);

        // when
        UserDto result = loginAndRegisterFacade.findByUsername(username);

        // then
        assertThat(result.username()).isEqualTo(username);
        assertThat(result.password()).isEqualTo(password);
    }
}