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
    private long total_teachers;
    private long verified_teachers;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Content {
        private Long admin_id;
        private List<TeachersDTO> teachers;
    }
}
