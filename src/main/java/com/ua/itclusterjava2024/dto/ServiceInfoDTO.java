package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceInfoDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PositionDTO> position;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EducationLevelDTO> education_levels;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TeachersDTO> teachers;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UniversityDTO> university;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SpecialtyDTO> specialty;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EducationProgramsDTO> education_program;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DisciplinesDTO> discipline;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DisciplineBlocksDTO> disciplineBlocks;
}
