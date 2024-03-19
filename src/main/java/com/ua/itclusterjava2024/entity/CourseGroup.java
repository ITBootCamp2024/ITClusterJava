package com.ua.itclusterjava2024.entity;

import jakarta.persistence.Access;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="course_groupes")
public class CourseGroup {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name="name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "type_id")
    Long typeId;
}
