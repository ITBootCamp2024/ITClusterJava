package com.ua.itclusterjava2024.dto;

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
    private List<PositionDTO> position;
    private List<EducationLevelDTO> education_levels;
    private List<TeachersDTO> teachers;
    private List<UniversityDTO> university;
    private List<SpecialtyDTO> specialty;
    private List<EducationProgramsDTO> education_program;
    private List<DisciplinesDTO> discipline;
    private List<DisciplineBlocksDTO> disciplineBlocks;
}
