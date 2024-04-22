package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.response.ErrorResponse;
import com.ua.itclusterjava2024.dto.response.LoginResponse;
import com.ua.itclusterjava2024.dto.response.RegisterResponse;
import com.ua.itclusterjava2024.dto.request.LoginRequest;
import com.ua.itclusterjava2024.dto.request.RegisterRequest;
import com.ua.itclusterjava2024.exceptions.EmailAlreadyExistsException;
import com.ua.itclusterjava2024.service.implementation.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RegisterResponse registerUser(@ModelAttribute RegisterRequest request) {
        return authenticationService.signUp(request);
    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<LoginResponse> loginUser(@ModelAttribute LoginRequest loginRequest) {
        LoginResponse response = authenticationService.signIn(loginRequest);
        return ResponseEntity.ok(response);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
