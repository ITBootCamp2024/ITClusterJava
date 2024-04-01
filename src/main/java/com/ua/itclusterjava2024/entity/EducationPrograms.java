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
@Table(name = "education_programs")
public class EducationPrograms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "Name of education_programs have to contain up to 200 symbols")
    private String name;

    @ManyToOne
    @JoinColumn(name = "education_level_id")
    private EducationLevels educationLevels;

    @Column(name = "guarantor", nullable = false)
    @NotEmpty
    @Size(max = 100, message = "guarantor have to contain up to 100 symbols")
    private String guarantor;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "program_url", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "Program link have to contain up to 200 symbols")
    private String program_url;

    @Column(name = "syllabus_link", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "Syllabus link have to contain up to 200 symbols")
    private String syllabus_link;
    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;
}
