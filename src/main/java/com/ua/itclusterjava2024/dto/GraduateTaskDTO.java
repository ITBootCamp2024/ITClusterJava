package com.ua.itclusterjava2024.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraduateTaskDTO implements Serializable {
    Long id;

    @NotNull
    @Size(max = 255)
    String name;

    @NotNull
    @NotBlank
    String controls;

    @Size(max = 255)
    String deadlines;
}