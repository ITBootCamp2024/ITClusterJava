package com.ua.itclusterjava2024.entity;

import jakarta.persistence.Access;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    @Size(max = 100, message = "Name of course group have to contain up to 100 symbols")
    String name;

    @Column(name = "description")
    @NotEmpty
    @Size(max = 200, message = "Description of course block have to contain up to 200 symbols")
    String description;

    @Column(name = "type_id")
    Long typeId;
}
