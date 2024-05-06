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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty
    @Size(max = 150, message = "Name of university have to contain up to 150 symbols")
    private String name;

    @Column(name = "abbr")
    @NotEmpty
    @Size(max = 45, message = "Abbr of university have to contain up to 45 symbols")
    private String abbr;

    @Column(name = "programs_list_url")
    @NotEmpty
    @Size(max = 255, message = "Programs_list_url of university have to contain up to 255 symbols")
    private String programsListUrl;

    @Column(name = "url")
    @NotEmpty
    @Size(max = 255, message = "Url of university have to contain up to 255 symbols")
    private String url;

}
