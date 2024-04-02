package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "specialty")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    @NotEmpty
    @Size(max = 45, message = "Name of specialty have to contain up to 200 symbols")
    private String code;
    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "Name of specialty have to contain up to 200 symbols")
    private String name;

    @Column(name = "standard_url", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "Standard_url of specialty have to contain up to 200 symbols")
    private String standard_url;

}
