package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class DisciplinesDTO {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100, message = "Name of discipline have to contain up to 100 symbols")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TeachersDTO teacher;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DisciplineBlocksDTO discipline_block;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DisciplineGroupsDTO discipline_group;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EducationProgramsDTO education_program;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Syllabus Link have to contain up to 255 symbols")
    private String syllabus_url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Syllabus Link have to contain up to 255 symbols")
    private String education_plan_url;
}
