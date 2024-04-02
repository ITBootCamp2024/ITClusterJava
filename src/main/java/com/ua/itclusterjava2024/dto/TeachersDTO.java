package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeachersDTO {
    private Long id;
    private String name;
    private PositionDTO position;
    private DegreeDTO degree;
    private UniversityDTO university;
    private DepartmentDTO department;
    private String email;
    private String comments;
}

