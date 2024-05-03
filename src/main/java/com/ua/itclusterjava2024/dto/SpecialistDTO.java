package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Getter
@Value
public class SpecialistDTO implements Serializable {
    Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255)
    String company;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    String position;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255)
    String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    String professionalField;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100)
    String disciplineType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    Integer experience;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255)
    String urlCv;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    RoleDTO role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    Boolean verified;
}