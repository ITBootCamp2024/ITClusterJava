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
    @Size(max = 200, message = "Description of discipline block have to contain up to 200 symbols")
    String description;
    @ManyToOne
    @JoinColumn(name = "block_id")
    DisciplineBlocks block_id;

    @Column(name = "discipline_url")
    @NotEmpty
    String discipline_url;

}
