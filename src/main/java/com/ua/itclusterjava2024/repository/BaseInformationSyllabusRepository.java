package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.BaseInformationSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseInformationSyllabusRepository extends JpaRepository<BaseInformationSyllabus, Long> {

    Optional<BaseInformationSyllabus> findBySyllabusId(Long syllabusId);
}