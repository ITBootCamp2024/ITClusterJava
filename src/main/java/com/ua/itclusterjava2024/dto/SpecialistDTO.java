package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SpecialistDTO implements Serializable {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255)
    private String company;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    private String position;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    private String professionalField;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    private String disciplineType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Integer experience;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255)
    private String urlCv;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private RoleDTO role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Boolean verified;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Long all_syllabuses;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Long syllabuses_for_review;
}