package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Reviews;

import java.util.List;

public interface ReviewsService extends Service<Reviews> {
    void updateAcceptedBySpecialistIdAndSyllabusId(Long specialistId, Long syllabusId, Boolean accepted);
    List<Reviews> findAcceptedBySpecialistId(Long specialistId);
}
