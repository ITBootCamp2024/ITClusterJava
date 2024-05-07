package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.entity.User;
import com.ua.itclusterjava2024.service.implementation.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class ExampleController {

    private final UserServiceImpl service;

    @GetMapping
    @Operation(summary = "Доступно авторизованим користувачам")
    public String example() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return "Ви авторизовані: \n" +
                "Ім'я: " + user.getFirstName() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Роль: " + user.getRole().getName();

    }

    @GetMapping("/admin")
    @Operation(summary = "Доступно авторизованим користувачам з роллю ADMIN")
    @PreAuthorize("hasAuthority('admin')")
    public String exampleAdmin() {
        return "Ви авторизовані як адміністратор!";
    }
}
