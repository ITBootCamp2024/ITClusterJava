package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseBlockDTO {
    @NotEmpty
    @Size(max = 100, message = "Name of course block have to contain up to 100 symbols")
    String name;

    @NotEmpty
    String description;
}
