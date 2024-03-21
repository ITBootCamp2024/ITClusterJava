package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.ProgramsLevel;
import com.ua.itclusterjava2024.entity.School;
import com.ua.itclusterjava2024.repository.ProgramsLevelRepository;
import com.ua.itclusterjava2024.service.interfaces.ProgramsLevelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramsLevelServiceImpl implements ProgramsLevelService {

    private final ProgramsLevelRepository programsLevelRepository;

    public ProgramsLevelServiceImpl(ProgramsLevelRepository programsLevelRepository) {
        this.programsLevelRepository = programsLevelRepository;
    }

    @Override
    public ProgramsLevel create(ProgramsLevel programsLevel) {
        return programsLevelRepository.save(programsLevel);
    }

    @Override
    public ProgramsLevel readById(long id) {
        Optional<ProgramsLevel> foundSchool = programsLevelRepository.findById(id);
        return foundSchool.orElse(null);
    }

    @Override
    public ProgramsLevel update(ProgramsLevel programsLevel) {
        return programsLevelRepository.save(programsLevel);
    }

    @Override
    public void delete(long id) {
        programsLevelRepository.deleteById(id);
    }

    @Override
    public List<ProgramsLevel> getAll() {
        return programsLevelRepository.findAll();
    }
}
