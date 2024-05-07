package com.ua.itclusterjava2024.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "table_number", nullable = false)
    private Integer tableNumber;

    @NotNull
    @Column(name = "question_number", nullable = false)
    private Integer questionNumber;

    @NotNull
    @Column(name = "answer", nullable = false)
    private Integer answer;

    @Lob
    @Column(name = "comment")
    private String comment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "review_id", nullable = false)
    private Reviews review;

}