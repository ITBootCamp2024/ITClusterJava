package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "name")
    @NotEmpty
    @Size(max = 100, message = "Name of school have to contain up to 100 symbols")
    String name;
    @Column(name = "site")
    @NotEmpty
    @Size(max = 100, message = "Site of school have to contain up to 100 symbols")
    String site;
    @Column(name = "description")
    @NotEmpty
    String description;
    //TODO relationship
    @Column(name = "university_id")
    long university_id;
    @Column(name = "contact")
    @NotEmpty
    @Size(max = 100, message = "Contact of school have to contain up to 100 symbols")
    String contact;
}
