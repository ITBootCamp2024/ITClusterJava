package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    @Size(max = 100, message = "Name of course have to contain up to 100 symbols")
    private String name;

    @Column(name = "type_id", nullable = false)
    private Integer type_id;

    @Column(name = "num_in_type", nullable = false)
    private Integer numInType;

    @Column(name = "syllabus_link", nullable = false)
    @Size(max = 200, message = "Syllabus Link have to contain up to 200 symbols")
    @Pattern(regexp = "https?://.", message = "Incorrect format URL")
    private String syllabusLink;

    @Column(name = "work_program_link", nullable = false)
    @Size(max = 200, message = "Work program link have to contain up to 200 symbols")
    private String workProgramLink;

    @Column(name = "program_id", nullable = false)
    private Integer program_id;

    @Column(name = "teacher_id", nullable = false)
    private Integer teacher_id;

    @Column(name = "review_link", nullable = false)
    @Size(max = 100, message = "Review Link have to contain up to 100 symbols")
    private String reviewLink;

    @Column(name = "course_status", nullable = false)
    private Integer courseStatus;
}
