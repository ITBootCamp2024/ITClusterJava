package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.EducationLevel;
import com.ua.itclusterjava2024.repository.EducationLevelsRepository;
import com.ua.itclusterjava2024.service.interfaces.EducationLevelsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationLevelsServiceImpl implements EducationLevelsService {

    private final EducationLevelsRepository educationLevelsRepository;

    public EducationLevelsServiceImpl(EducationLevelsRepository educationLevelsRepository) {
        this.educationLevelsRepository = educationLevelsRepository;
    }

    @Override
    public EducationLevel create(EducationLevel educationLevel) {
        return educationLevelsRepository.save(educationLevel);
    }

    @Override
    public Optional<EducationLevel> readById(long id) {
        return educationLevelsRepository.findById(id);
    }

    @Override
    public EducationLevel update(long id, EducationLevel educationLevel) {
        educationLevel.setId(id);
        return educationLevelsRepository.save(educationLevel);
    }

    @Override
    public void delete(long id) {
        educationLevelsRepository.deleteById(id);
    }

    @Override
    public Page<EducationLevel> getAll(Pageable pageable) {
        return educationLevelsRepository.findAll(pageable);
    }

    @Override
    public List<EducationLevel> getAll() {
        return educationLevelsRepository.findAll();
    }
}
