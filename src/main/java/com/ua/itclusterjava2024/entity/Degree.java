package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "degree")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
