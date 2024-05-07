package com.ua.itclusterjava2024.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursesDTO {
    private List<TeachersDTO> teacherDTO;
    private List<DisciplinesDTO> disciplinesDTO;
    private List<SyllabusesDTO> syllabusesDTO;
}
