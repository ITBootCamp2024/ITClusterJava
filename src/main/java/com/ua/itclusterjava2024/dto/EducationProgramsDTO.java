package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationProgramsDTO {
    private Long id;
    private String name;
    private EducationLevelsDTO educationLevels;
    private String guarantor;
    private DepartmentDTO department;
    private String programUrl;
    private String syllabusLink;
    private SpecialtyDTO specialty;
}
