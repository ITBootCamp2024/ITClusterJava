package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discipline_blocks")
public class DisciplineBlocks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 255, message = "Name of discipline block have to contain up to 255 symbols")
    private String name;

    @Column(name = "description")
    @NotEmpty
    private String description;
}
