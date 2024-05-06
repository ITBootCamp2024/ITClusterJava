package com.ua.itclusterjava2024.wrappers;

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
    private long total_teacher;
    private long verified_teacher;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Content {
        private List<TeachersDTO> teacher;
    }
}
