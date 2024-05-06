package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Reviews;

public interface ReviewsService extends Service<Reviews> {
    void updateAcceptedBySpecialistIdAndSyllabusId(Long specialistId, Long syllabusId, Boolean accepted);
}
