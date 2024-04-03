package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    private String education_level;
    private String name;
}
