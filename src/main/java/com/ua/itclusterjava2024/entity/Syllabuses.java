package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "syllabuses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Syllabuses {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_id")
    private Disciplines disciplines;
}
