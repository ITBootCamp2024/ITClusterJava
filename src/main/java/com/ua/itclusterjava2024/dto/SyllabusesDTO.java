package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyllabusesDTO {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Syllabus name have to contain up to 255 symbols")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 45, message = "Syllabus status name have to contain up to 45 symbols")
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 45, message = "Validation_event syllabus have to contain up to 45 symbols")
    private DisciplinesDTO disciplines;
}
