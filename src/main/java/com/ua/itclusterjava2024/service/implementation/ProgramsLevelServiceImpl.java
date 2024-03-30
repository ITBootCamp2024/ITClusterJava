package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.ProgramsLevel;
import com.ua.itclusterjava2024.repository.ProgramsLevelRepository;
import com.ua.itclusterjava2024.service.interfaces.ProgramsLevelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Optional<ProgramsLevel> readById(long id) {
        return programsLevelRepository.findById(id);
    }

    @Override
    public ProgramsLevel update(long id, ProgramsLevel programsLevel) {
        programsLevel.setId(id);
        return programsLevelRepository.save(programsLevel);
    }

    @Override
    public void delete(long id) {
        programsLevelRepository.deleteById(id);
    }

    @Override
    public Page<ProgramsLevel> getAll(Pageable pageable) {
        return programsLevelRepository.findAll(pageable);
    }

    @Override
    public List<ProgramsLevel> getAll() {
        return programsLevelRepository.findAll();
    }
}
