package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDTO {
    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String abbr;
    //поле необхідне для ServiceInfo
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DepartmentDTO> department;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String programs_list_url;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;
}
