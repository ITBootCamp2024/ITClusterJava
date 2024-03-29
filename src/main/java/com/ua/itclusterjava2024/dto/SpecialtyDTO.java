package com.ua.itclusterjava2024.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialtyDTO {
    @Id
    long id;

    @Size(max = 200, message = "Name of specialty have to contain up to 200 symbols")
    private String name;

    @Size(max = 200, message = "Link standart of specialty have to contain up to 200 symbols")
    private String link_standart;
}
