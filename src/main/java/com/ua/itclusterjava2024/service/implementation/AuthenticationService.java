package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.dto.response.LoginResponse;
import com.ua.itclusterjava2024.dto.request.LoginRequest;
import com.ua.itclusterjava2024.dto.request.RegisterRequest;
import com.ua.itclusterjava2024.entity.User;
import com.ua.itclusterjava2024.exceptions.NotFoundException;
import com.ua.itclusterjava2024.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse signIn(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));


        User user = (User) userService.userDetailsService()
                .loadUserByUsername(request.getEmail());

        String jwtToken = jwtService.generateToken(user);

        // TODO: Implement refresh token

        return new LoginResponse(jwtToken, "refresh_token", user.getRole().getName());
    }


    public User signUp(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirst_name())
                .lastName(request.getLast_name())
                .parentName(request.getParent_name())
                .phone(request.getPhone())
                .role(roleRepository.findByName("user").orElseThrow(() -> new NotFoundException("Role not found")))
                .activeStatus(true)
                .createdAt(Instant.now())
                .emailConfirmed(false)
                .build();

        userService.create(user);
        return user;
    }
}

