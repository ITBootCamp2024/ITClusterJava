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
@Table(name = "disciplines")
public class Disciplines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 100, message = "Name of discipline have to contain up to 100 symbols")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teachers teachers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_group_id")
    private DisciplineGroups discipline_group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_program_id")
    private EducationPrograms education_program;

    @Column(name = "syllabus_url", nullable = false)
    @NotEmpty
    @Size(max = 255, message = "Syllabus Link have to contain up to 255 symbols")
//    @Pattern(regexp = "https?://.", message = "Incorrect format URL")
    private String syllabus_url;

    @Column(name = "education_plan_url", nullable = false)
    @NotEmpty
    @Size(max = 255, message = "Syllabus Link have to contain up to 255 symbols")
 //   @Pattern(regexp = "https?://.", message = "Incorrect format URL")
    private String education_plan_url;


}
