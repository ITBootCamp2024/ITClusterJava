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
public class DisciplineBlocksDTO {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Name of discipline block have to contain up to 255 symbols")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private String description;

    //поле необхідне для ServiceInfo
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("discipline_groups")
    private List<DisciplineGroupsDTO> disciplineGroups;
}
