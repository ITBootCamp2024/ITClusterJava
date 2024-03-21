package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type_id", nullable = false)
    private Integer type_id;

    @Column(name = "num_in_type", nullable = false)
    private Integer numInType;

    @Column(name = "syllabus_link", nullable = false)
    private String syllabusLink;

    @Column(name = "work_program_link", nullable = false)
    private String workProgramLink;

    @Column(name = "program_id", nullable = false)
    private Integer program_id;

    @Column(name = "teacher_id", nullable = false)
    private Integer teacher_id;

    @Column(name = "review_link", nullable = false)
    private String reviewLink;

    @Column(name = "course_status", nullable = false)
    private Integer courseStatus;
}
