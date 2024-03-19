package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.ProgramsLevel;
import com.ua.itclusterjava2024.repository.ProgramsLevelRepository;
import com.ua.itclusterjava2024.service.interfaces.ProgramsLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return programsLevelRepository.getById(id);
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
