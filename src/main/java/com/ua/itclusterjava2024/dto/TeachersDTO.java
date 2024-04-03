package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private PositionDTO position;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private EducationLevelDTO education_level;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private UniversityDTO university;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private DepartmentDTO department;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String comments;
}

