package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    @Size(max = 50, message = "Name of teachers have to contain up to 50 symbols")
    private String name;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "education_level_id")
    private EducationLevel education_level;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "email")
    @NotEmpty
    @Size(max = 100, message = "Email of teachers have to contain up to 100 symbols")
    private String email;

    @Column(name = "comments")
    @NotEmpty
    private String comments;
}
