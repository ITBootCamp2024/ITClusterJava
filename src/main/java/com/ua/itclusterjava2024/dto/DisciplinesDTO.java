package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinesDTO {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100, message = "Name of discipline have to contain up to 100 symbols")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TeachersDTO teacher;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("discipline_block")
    private DisciplineBlocksDTO disciplineBlock;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("discipline_group")
    private DisciplineGroupsDTO disciplineGroups;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("education_program")
    private EducationProgramsDTO educationPrograms;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Syllabus Link have to contain up to 255 symbols")
    @JsonProperty("syllabus_url")
    private String syllabusUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Syllabus Link have to contain up to 255 symbols")
    @JsonProperty("education_plan_url")
    private String educationPlanUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Syllabus Link have to contain up to 255 symbols")
    @JsonProperty("syllabus")
    private SyllabusesDTO syllabuses;
}
