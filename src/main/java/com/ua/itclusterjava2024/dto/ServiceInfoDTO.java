package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfoDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PositionDTO> position;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("education_levels")
    private List<EducationLevelDTO> educationLevels;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TeachersDTO> teachers;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UniversityDTO> university;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SpecialtyDTO> specialty;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("education_program")
    private List<EducationProgramsDTO> educationProgram;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DisciplinesDTO> discipline;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("discipline_blocks")
    private List<DisciplineBlocksDTO> disciplineBlocks;
}
