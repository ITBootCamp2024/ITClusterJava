package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationLevelDTO {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 45, message = "Name of education level have to contain up to 45 symbols")
    @JsonProperty("education_level")
    private String educationLevel;

    @NotEmpty
    @Size(max = 45, message = "Name of education level have to contain up to 45 symbols")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
}
