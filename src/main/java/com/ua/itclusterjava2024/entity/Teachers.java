package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "name")
    @NotEmpty
    @Size(max = 50, message = "Teacher's name have to contain up to 50 symbols")
    String name;
    @Column(name = "role")
    @NotEmpty
    @Size(max = 50, message = "Teacher's role have to contain up to 50 symbols")
    String role;
    @Column(name = "status")
    @NotEmpty
    @Size(max = 100, message = "Teacher's status have to contain up to 100 symbols")
    String status;
    @Column(name = "email")
    @Email
    @NotEmpty
    @Size(max = 100, message = "Teacher's email have to contain up to 100 symbols")
    String email;
    @Column(name = "details")
    @NotEmpty
    @Size(max = 100, message = "Teacher's details have to contain up to 100 symbols")
    String details;
}
