package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("syllabus_id")
    private Long syllabusId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("review_id")
    private Long reviewId;

    @JsonProperty("table_number")
    private Integer tableNumber;

    @JsonProperty("question_number")
    private Integer questionNumber;

    private Integer answer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comment;

}
