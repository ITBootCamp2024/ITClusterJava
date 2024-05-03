package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"syllabus_id", "discipline", "discipline_block","status", "accepted"})
public class SyllabusDTO {

    @JsonProperty("syllabus_id")
    private Long syllabusId;

    private String discipline;

    @JsonProperty("discipline_block")
    private String disciplineBlock;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    private Boolean accepted;
}
