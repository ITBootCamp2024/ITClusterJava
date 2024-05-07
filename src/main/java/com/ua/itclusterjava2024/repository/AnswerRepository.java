package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByReviewId(Long reviewId);
    Long countAllByReviewId(Long reviewId);
}