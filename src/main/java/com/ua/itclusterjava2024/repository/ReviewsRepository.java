package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    long countBySpecialistId(long specialistId);
    long countBySpecialistIdAndAcceptedFalse(long specialistId);
    long countBySpecialistIdAndAcceptedTrue(long specialistId);
}
