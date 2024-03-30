package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseGroupDTO {
    @Id
    long id;
    @NotEmpty
    @Size(max = 100, message = "Name of course group have to contain up to 100 symbols")
    String name;

    @NotEmpty
    @Size(max = 200, message = "Description of course block have to contain up to 200 symbols")
    String description;

    Long typeId;
}
