package com.ua.itclusterjava2024.controller;

import com.ua.itclusterjava2024.service.implementation.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return "Ви авторизовані!";
    }

    @GetMapping("/admin")
    @Operation(summary = "Доступно авторизованим користувачам з роллю ADMIN")
    @PreAuthorize("hasAuthority('admin')")
    public String exampleAdmin() {
        return "Ви авторизовані як адміністратор!";
    }

    @GetMapping("/get-admin")
    @Operation(summary = "Отримати роль ADMIN (для демонстрації)")
    public void getAdmin() {
         service.getAdmin();
    }
}
