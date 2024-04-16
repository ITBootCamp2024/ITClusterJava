package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @Column(name = "hours_of_theoretical_topics")
    private Integer hoursOfTheoreticalTopics;

    @Lob
    @Column(name = "practice_topics")
    private String practiceTopics;

    @Column(name = "hours_of_practice_topics")
    private Integer hoursOfPracticeTopics;

}