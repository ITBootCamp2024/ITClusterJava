package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    @JsonProperty("table_number")
    private Integer tableNumber;

    @JsonProperty("question_number")
    private Integer questionNumber;

    private Integer answer;

    private String comment;

}
