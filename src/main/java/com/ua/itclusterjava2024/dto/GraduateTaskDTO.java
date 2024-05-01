package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraduateTaskDTO implements Serializable {
    Long id;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long syllabusId;

    @NotNull
    @Size(max = 255)
    String name;

    @NotNull
    @NotBlank
    String controls;

    @Size(max = 255)
    String deadlines;
}