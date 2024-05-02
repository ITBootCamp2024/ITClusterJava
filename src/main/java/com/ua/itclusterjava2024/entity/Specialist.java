package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "specialist")
public class Specialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "company", nullable = false)
    private String company;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 100)
    @NotNull
    @Column(name = "position", nullable = false, length = 100)
    private String position;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 100)
    @Column(name = "phone", length = 100)
    private String phone;

    @Size(max = 100)
    @NotNull
    @Column(name = "professional_field", nullable = false, length = 100)
    private String professionalField;

    @Size(max = 100)
    @NotNull
    @Column(name = "discipline_type", nullable = false, length = 100)
    private String disciplineType;

    @NotNull
    @Column(name = "experience", nullable = false)
    private Integer experience;

    @Size(max = 255)
    @Column(name = "url_cv")
    private String urlCv;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "verified", nullable = false)
    private Boolean verified = false;

}