package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
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
public class DisciplineGroupsDTO {
    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private DisciplineBlocksDTO block;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String disciplineUrl;
}