package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "syllabuses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Syllabuses {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "validation_status", nullable = false)
    private String validation_status;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "validation_event", nullable = false)
    private String validation_event;
}
