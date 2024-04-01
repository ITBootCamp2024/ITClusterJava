package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.EducationLevels;
import com.ua.itclusterjava2024.repository.ProgramsLevelRepository;
import com.ua.itclusterjava2024.service.interfaces.EducationLevelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationLevelsServiceImpl implements EducationLevelService {

    private final ProgramsLevelRepository programsLevelRepository;

    public EducationLevelsServiceImpl(ProgramsLevelRepository programsLevelRepository) {
        this.programsLevelRepository = programsLevelRepository;
    }

    @Override
    public EducationLevels create(EducationLevels educationLevels) {
        return programsLevelRepository.save(educationLevels);
    }

    @Override
    public Optional<EducationLevels> readById(long id) {
        return programsLevelRepository.findById(id);
    }

    @Override
    public EducationLevels update(long id, EducationLevels educationLevels) {
        educationLevels.setId(id);
        return programsLevelRepository.save(educationLevels);
    }

    @Override
    public void delete(long id) {
        programsLevelRepository.deleteById(id);
    }

    @Override
    public Page<EducationLevels> getAll(Pageable pageable) {
        return programsLevelRepository.findAll(pageable);
    }

    @Override
    public List<EducationLevels> getAll() {
        return programsLevelRepository.findAll();
    }
}
