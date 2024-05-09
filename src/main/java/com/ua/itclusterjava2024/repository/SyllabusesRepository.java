package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.Syllabuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusesRepository extends JpaRepository<Syllabuses, Long> {
    @Query("SELECT syllabus FROM Syllabuses syllabus WHERE syllabus.disciplines.id = :discipline_id")
    List<Syllabuses> findByDisciplineId(@Param("discipline_id") Long id);

    @Modifying
    @Query("UPDATE Syllabuses s SET s.status = ?2 WHERE s.id = ?1")
    void updateStatusBySyllabusId(@Param("id") Long id, String status);

    List<Syllabuses> findAllByStatus(String status);

    List<Syllabuses> findAllByStatusNot(String status);

    List<Syllabuses> findAllByStatusNotIn(List<String> statuses);
}

