package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EducationProgramsDTO {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SpecialtyDTO specialty;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Name of education_programs have to contain up to 255 symbols")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private UniversityDTO university;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private EducationLevelDTO education_level;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100, message = "guarantor have to contain up to 100 symbols")
    private String guarantor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private DepartmentDTO department;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Program link have to contain up to 255 symbols")
    private String program_url;

    @NotEmpty
    @Size(max = 255, message = "Syllabus link have to contain up to 255 symbols")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String syllabus_url;
}
