package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "programs")
public class Programs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "Name of programs have to contain up to 200 symbols")
    private String name;


    //TODO
    //relationship
    @Column(name = "specialty_id", nullable = false)
    private Integer specialty_id;

    @Column(name = "program_link", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "Program link have to contain up to 200 symbols")
    private String program_link;

    //TODO
    //relationship
    @Column(name = "university_id", nullable = false)
    private Integer university_id;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "garant", nullable = false)
    @NotEmpty
    @Size(max = 100, message = "Garant have to contain up to 100 symbols")
    private String garant;

    @Column(name = "school_name", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "School name have to contain up to 200 symbols")
    private String school_name;

    @Column(name = "school_link", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "School link have to contain up to 200 symbols")
    private String school_link;

    @Column(name = "clabus_link", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "Clabus link have to contain up to 200 symbols")
    private String clabus_link;
}
