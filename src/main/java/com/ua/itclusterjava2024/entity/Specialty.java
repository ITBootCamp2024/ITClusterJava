package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "specialty")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    @NotEmpty
    @Size(max = 45, message = "Code of specialty have to contain up to 45 symbols")
    private String code;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 100, message = "Name of specialty have to contain up to 100 symbols")
    private String name;

    @Column(name = "standard_url", nullable = false)
    @NotEmpty
    @Size(max = 255, message = "Standard_url of specialty have to contain up to 255 symbols")
    private String standardUrl;

}
