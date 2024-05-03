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
    private List<SpecialistDTO> specialists;
    private Long verified_specialists_count;
    private Long not_verified_specialists_count;
}
