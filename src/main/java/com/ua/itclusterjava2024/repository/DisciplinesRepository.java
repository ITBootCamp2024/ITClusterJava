package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.entity.DisciplineGroups;
import com.ua.itclusterjava2024.entity.Disciplines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinesRepository extends JpaRepository<Disciplines, Long> {
    @Query("SELECT discipline FROM Disciplines discipline WHERE discipline.teachers.id = :teacher_id")
    List<Disciplines> findByTeacherId(@Param("teacher_id") Long id);
}
