package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Reviews;
import com.ua.itclusterjava2024.entity.Syllabuses;
import com.ua.itclusterjava2024.repository.ReviewsRepository;
import com.ua.itclusterjava2024.repository.SyllabusesRepository;
import com.ua.itclusterjava2024.service.interfaces.SyllabusesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SyllabusesServiceImpl implements SyllabusesService {

    private final SyllabusesRepository syllabusesRepository;
    private final ReviewsRepository reviewsRepository;

    public SyllabusesServiceImpl(SyllabusesRepository syllabusesRepository, ReviewsRepository reviewsRepository) {
        this.syllabusesRepository = syllabusesRepository;
        this.reviewsRepository = reviewsRepository;
    }


    @Override
    public Syllabuses create(Syllabuses syllabuses) {
        return syllabusesRepository.save(syllabuses);
    }

    @Override
    public Optional<Syllabuses> readById(long id) {
        return syllabusesRepository.findById(id);
    }

    @Override
    public Syllabuses update(long id, Syllabuses syllabuses) {
        syllabuses.setId(id);
        return syllabusesRepository.save(syllabuses);
    }

    @Override
    public void delete(long id) {
        syllabusesRepository.deleteById(id);
    }

    @Override
    public List<Syllabuses> getAll() {
        return syllabusesRepository.findAll();
    }

    @Override
    public Page<Syllabuses> getAll(Pageable pageable) {
        return syllabusesRepository.findAll(pageable);
    }

    @Override
    public List<Syllabuses> findByDisciplineId(Long disciplineId) {
        return syllabusesRepository.findByDisciplineId(disciplineId);
    }

    @Override
    public List<Syllabuses> findSyllabusesBySpecialistId(Long specialistId, boolean accepted) {
        if (accepted) {
            return reviewsRepository.findBySpecialistIdAndAcceptedTrue(specialistId)
                    .stream()
                    .map(Reviews::getSyllabus)
                    .toList();
        }
        return reviewsRepository.findBySpecialistIdAndAcceptedFalse(specialistId)
                .stream()
                .map(Reviews::getSyllabus)
                .toList();
    }
}
