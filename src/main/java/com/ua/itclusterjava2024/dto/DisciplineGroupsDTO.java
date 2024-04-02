package com.ua.itclusterjava2024.dto;

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
    private String description;
    private DisciplineBlocksDTO block;
    private String disciplineUrl;
}