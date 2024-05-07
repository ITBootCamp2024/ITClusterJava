package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineGroupsDTO {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 100, message = "Name of discipline group have to contain up to 100 symbols")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DisciplineBlocksDTO block;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Discipline_url of discipline_groups have to contain up to 255 symbols")
    @JsonProperty("discipline_url")
    private String disciplineUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @JsonProperty("discipline")
    private List<DisciplinesDTO> disciplines;
}