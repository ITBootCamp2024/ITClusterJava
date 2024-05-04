package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "teachers")
public class Teachers {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty
    @Size(max = 50, message = "Name of teachers have to contain up to 50 symbols")
    private String name;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "degree_level")
    @NotEmpty
    @Size(max = 50, message = "Degree level have to contain up to 50 symbols")
    private String degreeLevel;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "email")
    @NotEmpty
    @Size(max = 100, message = "Email of teachers have to contain up to 100 symbols")
    private String email;

    @Column(name = "comments")
    @NotEmpty
    private String comments;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "verified", nullable = false)
    private Boolean verified = false;
}
