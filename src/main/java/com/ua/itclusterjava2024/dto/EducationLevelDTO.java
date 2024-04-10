package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EducationLevelDTO {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 45, message = "Name of education level have to contain up to 45 symbols")
    private String education_level;

    @NotEmpty
    @Size(max = 45, message = "Name of education level have to contain up to 45 symbols")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
}
