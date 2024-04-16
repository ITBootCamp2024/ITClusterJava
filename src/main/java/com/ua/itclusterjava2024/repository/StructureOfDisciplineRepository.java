package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.StructureOfDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureOfDisciplineRepository extends JpaRepository<StructureOfDiscipline, Long> {
}