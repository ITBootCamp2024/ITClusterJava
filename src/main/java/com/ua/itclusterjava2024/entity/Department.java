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
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 100, message = "Name of department have to contain up to 100 symbols")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id")
    private University university;

    @Column(name = "description", nullable = false)
    @NotEmpty
    private String description;

    @Column(name = "address")
    @NotEmpty
    @Size(max = 255, message = "Address of department have to contain up to 255 symbols")
    private String address;

    @Column(name = "email")
    @NotEmpty
    @Size(max = 45, message = "Email of department have to contain up to 45 symbols")
    private String email;

    @Column(name = "phone")
    @NotEmpty
    @Size(max = 45, message = "Phone of department have to contain up to 45 symbols")
    private String phone;

    @Column(name = "url")
    @NotEmpty
    @Size(max = 255, message = "Url of department have to contain up to 255 symbols")
    private String url;

}
