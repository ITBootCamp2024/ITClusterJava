package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeachersDTO {
    private Long id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PositionDTO position;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 50, message = "Degree level have to contain up to 50 symbols")
    @JsonProperty("degree_level")
    private String degreeLevel;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UniversityDTO university;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DepartmentDTO department;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RoleDTO role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100, message = "Email of teachers have to contain up to 100 symbols")
    @Email
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private String comments;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    Boolean verified;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @JsonProperty("all_syllabuses")
    private Long allSyllabuses;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @JsonProperty("syllabuses_for_review")
    private Long syllabusesForReview;
}

