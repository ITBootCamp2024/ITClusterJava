package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"syllabus_id", "discipline", "discipline_block", "accepted"})
public class ProposedSyllabusDTO {

    @JsonProperty("syllabus_id")
    private Long syllabusId;

    private String discipline;

    @JsonProperty("discipline_block")
    private String disciplineBlock;

    private Boolean accepted;
}
