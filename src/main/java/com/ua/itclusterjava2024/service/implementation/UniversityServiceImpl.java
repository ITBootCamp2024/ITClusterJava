package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.University;
import com.ua.itclusterjava2024.repository.UniversityRepository;
import com.ua.itclusterjava2024.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return universityRepository.getById(id);
    }

    @Override
    public University update(University university) {
        return universityRepository.save(university);
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
