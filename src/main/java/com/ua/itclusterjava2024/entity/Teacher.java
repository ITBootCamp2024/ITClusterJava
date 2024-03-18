package com.ua.itclusterjava2024.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class Teacher {
    @Id
    @Column(name = "id")
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "role")
    String role;
    @Column(name = "status")
    String status;
    @Column(name = "email")
    String email;
    @Column(name = "details")
    String details;
}
