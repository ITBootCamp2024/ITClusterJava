package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"syllabus_id", "answers"})
public class SyllabusAnswersDTO {

    @JsonProperty("syllabus_id")
    private Long syllabusId;

    private List<AnswerDTO> answers;

}
