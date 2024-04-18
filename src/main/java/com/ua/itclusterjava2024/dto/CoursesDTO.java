package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursesDTO {
    private List<TeachersDTO> teacherDTO;
    private List<DisciplinesDTO> disciplinesDTO;
    private List<SyllabusesDTO> syllabusesDTO;
}
