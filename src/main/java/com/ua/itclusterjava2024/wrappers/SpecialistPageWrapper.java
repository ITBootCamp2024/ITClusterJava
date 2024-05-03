package com.ua.itclusterjava2024.wrappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ua.itclusterjava2024.dto.SpecialistDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialistPageWrapper {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> specialists;
}
