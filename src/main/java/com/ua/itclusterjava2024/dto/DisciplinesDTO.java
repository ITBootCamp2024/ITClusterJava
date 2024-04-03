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

    private Long teacherId;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Long disciplineGroupId;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Long educationProgramId;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String syllabusLink;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String educationPlanUrl;
}
