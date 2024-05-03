package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Reviews;
import com.ua.itclusterjava2024.repository.ReviewsRepository;
import com.ua.itclusterjava2024.service.interfaces.ReviewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public long getAllReviewsCount(long specialistId) {
        return reviewsRepository.countBySpecialistId(specialistId);
    }

    @Override
    public long getAllReviewsCountAcceptedTrue(long specialistId) {
        return reviewsRepository.countBySpecialistIdAndAcceptedTrue(specialistId);
    }

    @Override
    public long getAllReviewsCountAcceptedFalse(long specialistId) {
        return reviewsRepository.countBySpecialistIdAndAcceptedFalse(specialistId);
    }
}
