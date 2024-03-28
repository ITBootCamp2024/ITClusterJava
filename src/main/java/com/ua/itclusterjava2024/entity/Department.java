package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "deparment") //TODO Помилка в назві таблиці у SQL-дампі, повинно бути department
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "university")
    private University university;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "url")
    private String url;

}
