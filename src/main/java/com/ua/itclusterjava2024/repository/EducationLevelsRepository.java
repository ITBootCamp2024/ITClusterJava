package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.EducationLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationLevelsRepository extends JpaRepository<EducationLevel, Long> {
}
