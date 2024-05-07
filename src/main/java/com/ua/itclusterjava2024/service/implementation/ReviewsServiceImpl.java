package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Reviews;
import com.ua.itclusterjava2024.repository.ReviewsRepository;
import com.ua.itclusterjava2024.service.interfaces.ReviewsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;

    public ReviewsServiceImpl(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public Reviews create(Reviews specialty) {
        return reviewsRepository.save(specialty);
    }

    @Override
    public Optional<Reviews> readById(long id) {
        return reviewsRepository.findById(id);
    }

    @Override
    public Reviews update(long id, Reviews reviews) {
        reviews.setId(id);
        return reviewsRepository.save(reviews);
    }

    @Override
    public void delete(long id) {
        reviewsRepository.deleteById(id);
    }

    @Override
    public List<Reviews> getAll() {
        return reviewsRepository.findAll();
    }

    @Override
    public Page<Reviews> getAll(Pageable pageable) {
        return reviewsRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void updateAcceptedBySpecialistIdAndSyllabusId(Long specialistId, Long syllabusId, Boolean accepted) {
        if (Boolean.FALSE.equals(reviewsRepository.existsBySpecialistIdAndSyllabusId(specialistId, syllabusId)))
            throw new EntityNotFoundException("Review with specialist_id " + specialistId + " and syllabus_id " + syllabusId + " not found");
        reviewsRepository.updateAcceptedBySpecialistIdAndSyllabusId(specialistId, syllabusId, accepted);
    }

    @Override
    public List<Reviews> findAcceptedBySpecialistId(Long specialistId) {
        return reviewsRepository.findBySpecialistIdAndAcceptedTrue(specialistId);
    }

    @Override
    public Optional<Reviews> findAcceptedBySpecialistIdAndSyllabusId(Long specialistId, Long syllabusId) {
        return reviewsRepository.findBySpecialistIdAndSyllabusIdAndAcceptedTrue(specialistId, syllabusId);
    }
}
