package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachersDTO {
    @NotEmpty
    @Size(max = 50, message = "Teacher's name have to contain up to 50 symbols")
    String name;
    @NotEmpty
    @Size(max = 50, message = "Teacher's role have to contain up to 50 symbols")
    String role;
    @NotEmpty
    @Size(max = 100, message = "Teacher's status have to contain up to 100 symbols")
    String status;

    @NotEmpty
    @Size(max = 100, message = "Teacher's email have to contain up to 100 symbols")
    String email;
    @NotEmpty
    @Size(max = 100, message = "Teacher's details have to contain up to 100 symbols")
    String details;
}
