package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityDTO {
    @Id
    long id;
    @NotEmpty
    @Size(max = 200, message = "Name of university have to contain up to 200 symbols")
    private String name;
//
//    @NotEmpty
//    @Size(max = 20, message = "Shortname of university have to contain up to 20 symbols")
//    private String shortname;
//
//    @NotEmpty
//    @Size(max = 100, message = "Site link of university have to contain up to 100 symbols")
//    private String siteLink;
//
//    private String programs_list;
}
