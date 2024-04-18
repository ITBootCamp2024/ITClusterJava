package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.Disciplines;
import com.ua.itclusterjava2024.entity.Syllabuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusesRepository extends JpaRepository<Syllabuses, Long> {
    @Query("SELECT syllabus FROM Syllabuses syllabus WHERE syllabus.disciplines.id = :discipline_id")
    List<Syllabuses> findByDisciplineId(@Param("discipline_id") Long id);
}

