package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SpecialtyDTO {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String standardUrl;
}
