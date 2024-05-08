package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    @JsonProperty("syllabus_id")
    @NotNull
    private Long syllabusId;

    @JsonProperty("specialist_id")
    @NotNull
    private Long specialistId;
}
