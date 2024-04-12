package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Syllabuses;
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

    public SyllabusesServiceImpl(SyllabusesRepository syllabusesRepository) {
        this.syllabusesRepository = syllabusesRepository;
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
}