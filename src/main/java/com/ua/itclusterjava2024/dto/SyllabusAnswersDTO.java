package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"syllabus_id", "answers"})
public class SyllabusAnswersDTO {

    @JsonProperty("syllabus_id")
    private Long syllabusId;

    private List<AnswerDTO> answers;

}
