package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ua.itclusterjava2024.entity.Specialty;
import com.ua.itclusterjava2024.entity.Syllabuses;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private Integer studentCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Integer course;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Integer semester;

}