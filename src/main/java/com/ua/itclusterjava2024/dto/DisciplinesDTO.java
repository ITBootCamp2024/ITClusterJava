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
public class DisciplinesDTO {
    private Long id;
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

    private String syllabus_url;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String education_plan_url;
}
