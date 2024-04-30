package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.dto.request.ChangePasswordRequest;
import com.ua.itclusterjava2024.dto.response.MessageResponse;
import com.ua.itclusterjava2024.dto.response.LoginResponse;
import com.ua.itclusterjava2024.dto.response.RegisterResponse;
import com.ua.itclusterjava2024.dto.request.LoginRequest;
import com.ua.itclusterjava2024.dto.request.RegisterRequest;
import com.ua.itclusterjava2024.exceptions.EmailAlreadyExistsException;
import com.ua.itclusterjava2024.exceptions.JwtTokenException;
import com.ua.itclusterjava2024.exceptions.MissingAuthorizationHeaderException;
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

    @PostMapping(value = "/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.isEmpty())
            throw new MissingAuthorizationHeaderException("Missing Authorization Header");

        if (!authorizationHeader.startsWith("Bearer "))
            throw new JwtTokenException("Missing 'Bearer' type in 'Authorization' header. Expected 'Authorization: Bearer <JWT>'");

        String refreshToken = authorizationHeader.substring("Bearer ".length());
        LoginResponse response = authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<MessageResponse> changePassword(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                                 @ModelAttribute ChangePasswordRequest changePasswordRequest) {
        if (authorizationHeader == null || authorizationHeader.isEmpty())
            throw new MissingAuthorizationHeaderException("Missing Authorization Header");

        if (!authorizationHeader.startsWith("Bearer "))
            throw new JwtTokenException("Missing 'Bearer' type in 'Authorization' header. Expected 'Authorization: Bearer <JWT>'");

        String accessToken = authorizationHeader.substring("Bearer ".length());
        MessageResponse response = authenticationService.changePassword(changePasswordRequest, accessToken);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<MessageResponse> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>(new MessageResponse(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<MessageResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(new MessageResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
