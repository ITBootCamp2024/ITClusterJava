package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teachers")
public class Teachers {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "education_level_id")
    private EducationLevels educationLevel;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(name = "email")
    private String email;
    @Column(name = "comments")
    private String comments;
}
