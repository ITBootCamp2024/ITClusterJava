package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
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
    private String name;


    //TODO
    //relationship
    @Column(name = "specialty_id", nullable = false)
    private Integer specialty_id;

    @Column(name = "program_link", nullable = false)
    private String program_link;

    //TODO
    //relationship
    @Column(name = "university_id", nullable = false)
    private Integer university_id;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "garant", nullable = false)
    private String garant;

    @Column(name = "school_name", nullable = false)
    private String school_name;

    @Column(name = "school_link", nullable = false)
    private String school_link;

    @Column(name = "clabus_link", nullable = false)
    private String clabus_link;
}
