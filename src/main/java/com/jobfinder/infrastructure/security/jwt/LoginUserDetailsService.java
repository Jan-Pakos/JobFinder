package com.jobfinder.infrastructure.security.jwt;

import com.jobfinder.domain.login.LoginAndRegisterFacade;
import com.jobfinder.domain.login.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
class LoginUserDetailsService implements UserDetailsService {

    private final LoginAndRegisterFacade loginAndRegisterFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userByUsername = loginAndRegisterFacade.findByUsername(username);
        return getUser(userByUsername);
    }

    private User getUser(UserDto userDto) {
        return (User) User.builder()
                .username(userDto.username())
                .password(userDto.password())
                .roles("USER") // roles is forced here, must implement roles properly later
                .build();
    }
}
