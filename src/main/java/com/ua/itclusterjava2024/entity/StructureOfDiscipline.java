package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "structure_of_discipline")
public class StructureOfDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "module_id", nullable = false)
    private SyllabusModule module;

    @NotNull
    @Lob
    @Column(name = "theoretical_topic", nullable = false)
    private String theoreticalTopic;

    @Column(name = "theoretical_hours")
    private Integer theoreticalHours;

    @Lob
    @Column(name = "practice_topics")
    private String practiceTopics;

    @Column(name = "practice_hours")
    private Integer practiceHours;

}