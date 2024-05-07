package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.dto.request.ChangePasswordRequest;
import com.ua.itclusterjava2024.dto.response.LoginResponse;
import com.ua.itclusterjava2024.dto.request.LoginRequest;
import com.ua.itclusterjava2024.dto.request.RegisterRequest;
import com.ua.itclusterjava2024.dto.response.MessageResponse;
import com.ua.itclusterjava2024.dto.response.RegisterResponse;
import com.ua.itclusterjava2024.entity.User;
import com.ua.itclusterjava2024.exceptions.JwtTokenException;
import com.ua.itclusterjava2024.repository.RoleRepository;
import com.ua.itclusterjava2024.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final EmailService emailService;


    public LoginResponse signIn(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        User user = (User) userService.userDetailsService().loadUserByUsername(request.getEmail());

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponse(accessToken, refreshToken, user.getRole().getName());
    }

    public RegisterResponse signUp(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirst_name())
                .lastName(request.getLast_name())
                .parentName(request.getParent_name())
                .phone(request.getPhone())
                .role(roleRepository.findByName("user").orElseThrow(() -> new EntityNotFoundException("Role 'user' not found")))
                .activeStatus(true)
                .createdAt(Instant.now())
                .emailConfirmed(false)
                .build();
        //TODO send email
//        String token = jwtService.generateAccessToken(user);
//        emailService.sendConfirmationEmail(token, user.getEmail());
        User saved = userService.create(user);
        return new RegisterResponse(saved);
    }


    public LoginResponse refreshToken(String refreshToken) {
        if (!jwtService.isRefreshToken(refreshToken))
            throw new JwtTokenException("Only refresh tokens are allowed");

        String email = jwtService.extractEmail(refreshToken);
        User user = (User) userService.userDetailsService().loadUserByUsername(email);

        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);
        return new LoginResponse(newAccessToken, newRefreshToken, user.getRole().getName());
    }


    public MessageResponse changePassword(ChangePasswordRequest request, String accessToken) {
        if (!jwtService.isAccessToken(accessToken))
            throw new JwtTokenException("Only access tokens are allowed");

        String userEmail = jwtService.extractEmail(accessToken);
        User user = (User) userService.userDetailsService().loadUserByUsername(userEmail);

        if (!passwordEncoder.matches(request.getOld_password(), user.getPassword())) {
            throw new BadCredentialsException("The old password is incorrect");
        }
        user.setPasswordHash(passwordEncoder.encode(request.getNew_password()));
        userService.update(user.getId(), user);
        return new MessageResponse("Password changed");
    }
}

