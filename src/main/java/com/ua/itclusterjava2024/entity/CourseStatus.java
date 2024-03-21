package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "course_statuses")
public class CourseStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name="name")
    @NotEmpty
    @Size(max = 100, message = "Name of course status have to contain up to 100 symbols")
    String name;

    @Column(name="description")
    @NotEmpty
    String description;
}
