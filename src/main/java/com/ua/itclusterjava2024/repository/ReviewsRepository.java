package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    List<Reviews> findBySpecialistIdAndAcceptedFalse(Long specialistId);

    List<Reviews> findBySpecialistIdAndAcceptedTrue(Long specialistId);

    Optional<Reviews> findBySpecialistIdAndSyllabusIdAndAcceptedTrue(Long specialistId, Long syllabusId);

    Boolean existsBySpecialistIdAndSyllabusId(Long specialistId, Long syllabusId);

    @Modifying
    @Query("UPDATE Reviews r SET r.accepted = :accepted " +
            "WHERE r.specialist.id = :specialistId AND r.syllabus.id = :syllabusId")
    void updateAcceptedBySpecialistIdAndSyllabusId(@Param("specialistId") Long specialistId,
                                                   @Param("syllabusId") Long syllabusId,
                                                   @Param("accepted") Boolean accepted);
}
