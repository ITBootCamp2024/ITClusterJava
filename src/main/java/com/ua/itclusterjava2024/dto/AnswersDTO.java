package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswersDTO {

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
