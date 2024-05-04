package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "discipline_information")
public class DisciplineInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "syllabus_id", nullable = false)
    private Syllabuses syllabus;

    @Size(max = 255)
    @Column(name = "program_url")
    private String programUrl;

    @Lob
    @Column(name = "goal")
    private String goal;

    @Lob
    @Column(name = "competencies_list")
    private String competenciesList;

    @Lob
    @Column(name = "technologies_list")
    private String technologiesList;

    @Lob
    @Column(name = "graduate_task")
    private String graduateTask;

    @Column(name = "lecture")
    private Integer lecture;

    @Column(name = "laboratory")
    private Integer laboratory;

    @Column(name = "practice")
    private Integer practice;

    @Column(name = "self_study")
    private Integer selfStudy;

    @Lob
    @Column(name = "required_skills")
    private String requiredSkills;

    @Lob
    @Column(name = "university_logistics")
    private String universityLogistics;

    @Lob
    @Column(name = "self_logistics")
    private String selfLogistics;

}