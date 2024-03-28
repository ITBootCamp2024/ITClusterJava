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
    @Size(max = 100, message = "Teacher's name have to contain up to 100 symbols")
    String name;
    @Column(name = "position")
    @NotEmpty
    @Size(max = 100, message = "Teacher's position have to contain up to 100 symbols")
    String position;
    @Column(name = "degree")
    @NotEmpty
    @Size(max = 100, message = "Teacher's degree have to contain up to 100 symbols")
    String degree;
    @Column(name = "university")
    @NotEmpty
    @Size(max = 100, message = "Teacher's university have to contain up to 100 symbols")
    String university;
    @Column(name = "department")
    @NotEmpty
    @Size(max = 100, message = "Teacher's department have to contain up to 100 symbols")
    String department;
    @Column(name = "email")
    @Email
    @NotEmpty
    @Size(max = 100, message = "Teacher's email have to contain up to 100 symbols")
    String email;
    @Column(name = "comments")
    @NotEmpty
    @Size(max = 100, message = "Teacher's comments have to contain up to 100 symbols")
    String comments;
}
