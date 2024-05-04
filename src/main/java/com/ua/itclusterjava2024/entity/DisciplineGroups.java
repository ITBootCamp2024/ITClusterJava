package com.ua.itclusterjava2024.entity;

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
@Table(name = "discipline_groups")
public class DisciplineGroups {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    Long id;

    @Column(name = "name")
    @NotEmpty
    @Size(max = 100, message = "Name of discipline group have to contain up to 100 symbols")
    String name;

    @Column(name = "description")
    @NotEmpty
    String description;

    @ManyToOne
    @JoinColumn(name = "block_id")
    DisciplineBlocks disciplineBlocks;

    @Column(name = "discipline_url")
    @NotEmpty
    @Size(max = 255, message = "Discipline_url of discipline_groups have to contain up to 255 symbols")
    String disciplineUrl;

}
