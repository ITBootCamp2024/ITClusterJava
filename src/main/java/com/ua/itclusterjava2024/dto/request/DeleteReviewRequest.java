package com.ua.itclusterjava2024.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteReviewRequest {
    @JsonProperty("syllabus_id")
    private Long syllabusId;
    @JsonProperty("specialist_id")
    private Long specialistId;
}
