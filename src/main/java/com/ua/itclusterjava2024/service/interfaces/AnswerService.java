package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Answer;

import java.util.List;

public interface AnswerService extends Service<Answer>{
    List<Answer> findAllByReviewId(Long reviewId);
}
