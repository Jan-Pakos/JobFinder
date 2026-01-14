package com.jobfinder.domain.login;

import com.jobfinder.domain.login.dto.RegistrationResultDto;
import com.jobfinder.domain.login.dto.UserDto;
import com.jobfinder.domain.login.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginAndRegisterFacade {

    private final UserRepository userRepository;

    public RegistrationResultDto registerUser(UserRequestDto userRequestDto) {
        String username = userRequestDto.username();
        String password = userRequestDto.password();
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("Username: " + username + " already exists");
        }
        User userToSave = User.builder()
                .username(username)
                .password(password)
                .build();

        User savedUser = userRepository.save(userToSave);
        return RegistrationResultDto.builder()
                .username(savedUser.username())
                .password(savedUser.password())
                .build();
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username).map(
                user -> UserDto.builder()
                        .id(user.id())
                        .username(username)
                        .password(user.password())
                        .build()
        ).orElseThrow(
                () -> new UserNotFoundException("User with username: " + username + " not found")
        );
    }
}
