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
    private long id;

    @NotEmpty
    @Size(max = 100, message = "Teacher's name must contain up to 100 characters")
    private String name;

    private PositionDTO position;
    private DegreeDTO degree;
    private UniversityDTO university;
    private DepartmentDTO department;

    @Email
    @NotEmpty
    @Size(max = 100, message = "Teacher's email must contain up to 100 characters")
    private String email;

    @NotEmpty
    @Size(max = 100, message = "Teacher's comments must contain up to 100 characters")
    private String comments;
}

