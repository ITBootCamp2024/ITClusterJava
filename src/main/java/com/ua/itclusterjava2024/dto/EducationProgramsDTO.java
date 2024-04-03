package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EducationProgramsDTO {
    private long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private SpecialtyDTO specialty;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private UniversityDTO university;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private EducationLevelDTO education_level;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String guarantor;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private DepartmentDTO department;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String program_url;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String syllabus_url;
}
