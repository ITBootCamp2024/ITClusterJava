package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="discipline_blocks")
public class DisciplineBlocks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name="name", nullable = false)
    @NotEmpty
    @Size(max = 100, message = "Name of discipline block have to contain up to 100 symbols")
    String name;

    @Column(name="description")
    @NotEmpty
    String description;
}
