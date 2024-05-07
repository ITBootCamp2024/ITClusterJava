package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseInformationSyllabusDTO {

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private SyllabusesDTO syllabus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private SpecialtyDTO specialty;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @JsonProperty("student_count")
    private Integer studentCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Integer course;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Integer semester;

}
