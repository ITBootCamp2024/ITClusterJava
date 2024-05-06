package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 150, message = "Name of university have to contain up to 150 symbols")
    private String name;

    @NotEmpty
    @Size(max = 45, message = "Abbr of university have to contain up to 45 symbols")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String abbr;

    //поле необхідне для ServiceInfo
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DepartmentDTO> department;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 255, message = "Programs_list_url of university have to contain up to 255 symbols")
    @JsonProperty("programs_list_url")
    private String programsListUrl;

    @NotEmpty
    @Size(max = 255, message = "Url of university have to contain up to 255 symbols")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;
}
