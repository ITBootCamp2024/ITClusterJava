package com.ua.itclusterjava2024.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.itclusterjava2024.dto.TeachersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherPageWrapper {

    private Content content;

    @JsonProperty("total_teacher")
    private long totalTeacher;

    @JsonProperty("verified_teacher")
    private long verifiedTeacher;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Content {
        private List<TeachersDTO> teacher;
    }
}
