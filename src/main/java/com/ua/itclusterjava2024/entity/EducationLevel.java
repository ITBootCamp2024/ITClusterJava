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
@Table(name = "education_levels")
public class EducationLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "education_level", nullable = false)
    @NotEmpty
    @Size(max = 45, message = "Name of education level have to contain up to 45 symbols")
    private String education_level;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 45, message = "Name of education level have to contain up to 45 symbols")
    private String name;
}
