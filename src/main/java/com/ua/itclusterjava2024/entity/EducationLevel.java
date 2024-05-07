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
@Table(name = "education_levels")
public class EducationLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "education_level", nullable = false)
    @NotEmpty
    @Size(max = 45, message = "Name of education level have to contain up to 45 symbols")
    private String educationLevel;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 45, message = "Name of education level have to contain up to 45 symbols")
    private String name;
}
