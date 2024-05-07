package com.ua.itclusterjava2024.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.itclusterjava2024.dto.DisciplineGroupsDTO;
import com.ua.itclusterjava2024.dto.SpecialistDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DisciplinePageWrapper {
    private Content content;
    private List<SpecialistDTO> specialist;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Content {
        @JsonProperty("admin_id")
        private Long adminId;
        private List<DisciplineGroupsDTO> disciplineGroups;
    }
}
