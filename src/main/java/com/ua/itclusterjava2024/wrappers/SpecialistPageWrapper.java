package com.ua.itclusterjava2024.wrappers;

import com.ua.itclusterjava2024.dto.SpecialistDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialistPageWrapper {
    private Content content;
    private long total_specialist;
    private long verified_specialist;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Content {
        private List<SpecialistDTO> specialist;
    }
}
