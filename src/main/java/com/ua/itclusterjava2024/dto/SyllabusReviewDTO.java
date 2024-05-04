package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"syllabus_id", "discipline", "discipline_block", "status", "accepted"})
public class SyllabusReviewDTO {

    @JsonProperty("syllabus_id")
    @NotNull
    private Long syllabusId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String discipline;

    @JsonProperty("discipline_block")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String disciplineBlock;

    @NotNull
    private Boolean accepted;
}
