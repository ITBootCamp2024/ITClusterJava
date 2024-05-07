package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonProperty("professional_field")
    private String professionalField;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    @JsonProperty("discipline_type")
    private String disciplineType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Integer experience;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255)
    @JsonProperty("url_cv")
    private String urlCv;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private RoleDTO role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private Boolean verified;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @JsonProperty("all_syllabuses")
    private Long allSyllabuses;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @JsonProperty("syllabuses_for_review")
    private Long syllabusesForReview;
}