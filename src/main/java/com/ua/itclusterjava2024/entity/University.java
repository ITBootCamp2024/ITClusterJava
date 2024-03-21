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
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    @Size(max = 200, message = "Name of university have to contain up to 200 symbols")
    private String name;

    @Column(name = "shortname", nullable = false)
    @NotEmpty
    @Size(max = 20, message = "Shortname of university have to contain up to 20 symbols")
    private String shortname;

    @Column(name = "sitelink", nullable = false)
    @NotEmpty
    @Size(max = 100, message = "Site link of university have to contain up to 100 symbols")
    private String siteLink;

    //TODO
    @Column(name = "programs_list", nullable = false)
    private String programs_list;
}
