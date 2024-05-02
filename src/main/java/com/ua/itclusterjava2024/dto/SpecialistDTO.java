package com.ua.itclusterjava2024.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Value
public class SpecialistDTO implements Serializable {
    Integer id;

    @NotNull
    @Size(max = 255)
    String company;

    @NotNull
    @Size(max = 100)
    String name;

    @NotNull
    @Size(max = 100)
    String position;

    @NotNull
    @Size(max = 255)
    String email;

    @Size(max = 100)
    String phone;

    @NotNull
    @Size(max = 100)
    String professionalField;

    @NotNull
    @Size(max = 100)
    String disciplineType;

    @NotNull
    Integer experience;

    @Size(max = 255)
    String urlCv;

    @NotNull
    RoleDTO role;

    @NotNull
    Boolean verified;
}