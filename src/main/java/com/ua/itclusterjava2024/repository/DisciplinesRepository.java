package com.ua.itclusterjava2024.repository;

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

    @Query("SELECT discipline FROM Disciplines discipline WHERE discipline.disciplineGroups.id = :discipline_group_id")
    List<Disciplines> findByDisciplineGroupId(@Param("discipline_group_id") Long id);
}
