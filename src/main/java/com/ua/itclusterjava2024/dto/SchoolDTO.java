package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolDTO {
    @NotEmpty
    @Size(max = 100, message = "Name of school have to contain up to 100 symbols")
    String name;
    @NotEmpty
    @Size(max = 100, message = "Site of school have to contain up to 100 symbols")
    String site;
    @NotEmpty
    String description;
    long university_id;
    @NotEmpty
    @Size(max = 100, message = "Contact of school have to contain up to 100 symbols")
    String contact;
}
