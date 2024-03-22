package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.School;
import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.repository.UniversityRepository;
import com.ua.itclusterjava2024.service.interfaces.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University create(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University readById(long id) {
        Optional<University> foundSchool = universityRepository.findById(id);
        return foundSchool.orElse(null);
    }

    @Override
    public University update(long id, University t) {
        t.setId(id);
        return universityRepository.save(t);
    }

    @Override
    public void delete(long id) {
        universityRepository.deleteById(id);
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }
}
