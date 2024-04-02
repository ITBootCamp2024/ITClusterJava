package com.ua.itclusterjava2024.dto;

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
    private Long teacherId;
    private Long disciplineGroupId;
    private Long educationProgramId;
    private String syllabusLink;
    private String educationPlanUrl;
}
