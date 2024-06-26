package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stakeholder")
public class Stakeholder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "syllabus_id", nullable = false)
    private Syllabuses syllabus;

    @Size(max = 255)
    @Column(name = "specialty")
    private String specialty;

    @Lob
    @Column(name = "vacancies")
    private String vacancies;

    @Lob
    @Column(name = "skills")
    private String skills;

    @Lob
    @Column(name = "relevant_materials")
    private String relevantMaterials;

    @Lob
    @Column(name = "borrowed_materials")
    private String borrowedMaterials;

}