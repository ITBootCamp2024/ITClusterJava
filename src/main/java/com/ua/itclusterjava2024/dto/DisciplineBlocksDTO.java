package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisciplineBlocksDTO {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
//поле необхідне для ServiceInfo
    private List<DisciplineGroupsDTO> disciplineGroups;
}
