package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Answer;
import com.ua.itclusterjava2024.repository.AnswerRepository;
import com.ua.itclusterjava2024.service.interfaces.AnswerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer create(Answer specialty) {
        return answerRepository.save(specialty);
    }

    @Override
    public Optional<Answer> readById(long id) {
        return answerRepository.findById(id);
    }

    @Override
    public Answer update(long id, Answer answer) {
        answer.setId(id);
        return answerRepository.save(answer);
    }

    @Override
    public void delete(long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    @Override
    public Page<Answer> getAll(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    @Override
    public List<Answer> findAllByReviewId(Long reviewId) {
        return answerRepository.findAllByReviewId(reviewId);
    }

    @Override
    public Long countAllByReviewId(Long reviewId) {
        return answerRepository.countAllByReviewId(reviewId);
    }
}
