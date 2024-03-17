package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="course_blocks")
public class CourseBlock {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    @Column(name="name", nullable = false)
    String name;

    @Column(name="description")
    String description;
}
