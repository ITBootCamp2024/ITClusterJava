package com.ua.itclusterjava2024.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.itclusterjava2024.dto.AnswersDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswersRequest {
    @JsonProperty("syllabus_id")
    private Long syllabusId;
    private List<AnswersDTO> answers;
}
