package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course_statuses")
public class CourseStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name="name")
    String name;

    @Column(name="description")
    String description;
}
