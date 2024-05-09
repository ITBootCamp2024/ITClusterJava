package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Reviews;
import com.ua.itclusterjava2024.entity.Specialist;

import java.util.List;
import java.util.Optional;

public interface ReviewsService extends Service<Reviews> {

    void updateAcceptedBySpecialistIdAndSyllabusId(Long specialistId, Long syllabusId, Boolean accepted);

    List<Reviews> findAcceptedBySpecialistId(Long specialistId);

    Optional<Reviews> findAcceptedBySpecialistIdAndSyllabusId(Long specialistId, Long syllabusId);
    Optional<Reviews> findBySpecialistIdAndSyllabusId(Long specialistId, Long syllabusId);
    void deleteBySpecialistIdAndSyllabusId(Long specialistId, Long syllabusId);
    Optional<Specialist> findSpecialistBySyllabusId(Long syllabusId);

}
