package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDTO {
    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String abbr;
    private String programsListUrl;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;
}
