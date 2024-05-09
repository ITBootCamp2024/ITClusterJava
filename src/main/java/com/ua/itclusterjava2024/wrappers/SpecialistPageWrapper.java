package com.ua.itclusterjava2024.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.itclusterjava2024.dto.SpecialistDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialistPageWrapper {
    private Content content;

    @JsonProperty("total_specialist")
    private long totalSpecialist;

    @JsonProperty("verified_specialist")
    private long verifiedSpecialist;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Content {
        private List<SpecialistDTO> specialist;
    }
}
