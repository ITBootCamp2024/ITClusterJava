package com.ua.itclusterjava2024.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgramsLevelDTO {
    @NotEmpty
    @Size(max = 100, message = "Name of program level have to contain up to 100 symbols")
    private String name;
}
