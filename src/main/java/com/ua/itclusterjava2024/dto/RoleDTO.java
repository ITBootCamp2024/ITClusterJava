package com.ua.itclusterjava2024.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements Serializable {
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    @Size(max = 45, message = "Name of the role have to contain up to 45 symbols")
    private String name;
}
