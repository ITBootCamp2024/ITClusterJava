package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgramsDTO {
    @NotEmpty
    @Size(max = 200, message = "Name of programs have to contain up to 200 symbols")
    private String name;

    private Integer specialty_id;

    @NotEmpty
    @Size(max = 200, message = "Program link have to contain up to 200 symbols")
    private String program_link;

    private Integer university_id;

    private Integer level;

    @NotEmpty
    @Size(max = 100, message = "Garant have to contain up to 100 symbols")
    private String garant;

    @NotEmpty
    @Size(max = 200, message = "School name have to contain up to 200 symbols")
    private String school_name;

    @NotEmpty
    @Size(max = 200, message = "School link have to contain up to 200 symbols")
    private String school_link;

    @NotEmpty
    @Size(max = 200, message = "Clabus link have to contain up to 200 symbols")
    private String clabus_link;
}
