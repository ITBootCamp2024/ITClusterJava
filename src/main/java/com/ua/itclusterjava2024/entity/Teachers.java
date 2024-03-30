package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teachers")
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position_id;

    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree_id;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department_id;

    @Column(name = "comments")
    private String comments;
}
