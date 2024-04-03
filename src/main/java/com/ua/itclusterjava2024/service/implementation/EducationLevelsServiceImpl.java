package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.EducationLevel;
import com.ua.itclusterjava2024.repository.ProgramsLevelRepository;
import com.ua.itclusterjava2024.service.interfaces.EducationLevelsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationLevelsServiceImpl implements EducationLevelsService {

    private final ProgramsLevelRepository programsLevelRepository;

    public EducationLevelsServiceImpl(ProgramsLevelRepository programsLevelRepository) {
        this.programsLevelRepository = programsLevelRepository;
    }

    @Override
    public EducationLevel create(EducationLevel educationLevel) {
        return programsLevelRepository.save(educationLevel);
    }

    @Override
    public Optional<EducationLevel> readById(long id) {
        return programsLevelRepository.findById(id);
    }

    @Override
    public EducationLevel update(long id, EducationLevel educationLevel) {
        educationLevel.setId(id);
        return programsLevelRepository.save(educationLevel);
    }

    @Override
    public void delete(long id) {
        programsLevelRepository.deleteById(id);
    }

    @Override
    public Page<EducationLevel> getAll(Pageable pageable) {
        return programsLevelRepository.findAll(pageable);
    }

    @Override
    public List<EducationLevel> getAll() {
        return programsLevelRepository.findAll();
    }
}
