package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.response.LoginResponse;
import com.ua.itclusterjava2024.dto.response.RegisterResponse;
import com.ua.itclusterjava2024.dto.request.LoginRequest;
import com.ua.itclusterjava2024.dto.request.RegisterRequest;
import com.ua.itclusterjava2024.entity.User;
import com.ua.itclusterjava2024.service.implementation.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RegisterResponse registerUser(@Valid @ModelAttribute RegisterRequest request) {
        User user = authenticationService.signUp(request);
        return convertToDto(user);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public LoginResponse loginUser(@Valid @ModelAttribute LoginRequest loginRequest) {
        return authenticationService.signIn(loginRequest);
    }

    private User convertToEntity(RegisterResponse registerResponse) {
        return modelMapper.map(registerResponse, User.class);
    }

    private RegisterResponse convertToDto(User user) {
        return modelMapper.map(user, RegisterResponse.class);
    }
}
