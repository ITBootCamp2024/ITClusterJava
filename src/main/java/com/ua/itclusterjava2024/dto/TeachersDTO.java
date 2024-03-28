package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Id;
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
    @Id
    long id;
    @NotEmpty
    @Size(max = 100, message = "Teacher's name have to contain up to 100 symbols")
    String name;
    @NotEmpty
    @Size(max = 100, message = "Teacher's position have to contain up to 100 symbols")
    String position;
    @NotEmpty
    @Size(max = 100, message = "Teacher's degree have to contain up to 100 symbols")
    String degree;
    @NotEmpty
    @Size(max = 100, message = "Teacher's university have to contain up to 100 symbols")
    String university;
    @NotEmpty
    @Size(max = 100, message = "Teacher's department have to contain up to 100 symbols")
    String department;
    @Email
    @NotEmpty
    @Size(max = 100, message = "Teacher's email have to contain up to 100 symbols")
    String email;
    @NotEmpty
    @Size(max = 100, message = "Teacher's comments have to contain up to 100 symbols")
    String comments;
}
