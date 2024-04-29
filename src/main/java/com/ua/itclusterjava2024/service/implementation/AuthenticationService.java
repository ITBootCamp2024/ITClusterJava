package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.dto.response.LoginResponse;
import com.ua.itclusterjava2024.dto.request.LoginRequest;
import com.ua.itclusterjava2024.dto.request.RegisterRequest;
import com.ua.itclusterjava2024.dto.response.RegisterResponse;
import com.ua.itclusterjava2024.entity.User;
import com.ua.itclusterjava2024.exceptions.JwtTokenException;
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

        String jwtToken = jwtService.generateAccessToken(user);

        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponse(jwtToken, refreshToken, user.getRole().getName());
    }

    public LoginResponse refreshToken(String refreshToken) {
        String tokenType = jwtService.extractClaim(refreshToken, claims -> claims.get("tokenType", String.class));
        if (!tokenType.equals("refresh"))
            throw new JwtTokenException("Only refresh tokens are allowed");

        String email = jwtService.extractEmail(refreshToken);
        User user = (User) userService.userDetailsService().loadUserByUsername(email);

        if (!jwtService.isTokenValid(refreshToken, user))
            throw new JwtTokenException("Invalid refresh token");

        String jwtToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);
        return new LoginResponse(jwtToken, newRefreshToken, user.getRole().getName());
    }

    public RegisterResponse signUp(RegisterRequest request) {
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

        return new RegisterResponse(userService.create(user));
    }
}

