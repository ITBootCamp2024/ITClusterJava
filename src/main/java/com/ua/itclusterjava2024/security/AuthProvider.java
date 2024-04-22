package com.ua.itclusterjava2024.security;

import com.ua.itclusterjava2024.entity.User;
import com.ua.itclusterjava2024.exceptions.EmailNotConfirmedException;
import com.ua.itclusterjava2024.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = (User) userService.userDetailsService().loadUserByUsername(email);

        if (user == null || (!user.getEmail().equals(email) && !user.getUsername().equals(email)))
            throw new UsernameNotFoundException("User with email " + email + " does not exist");

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("Incorrect password");

        if (Boolean.FALSE.equals(user.getEmailConfirmed()))
            throw new EmailNotConfirmedException("Email " + email + " is not confirmed. Please check your email");

        if (Boolean.FALSE.equals(user.getActiveStatus()))
            throw new LockedException("User with email " + email + " is banned");

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
