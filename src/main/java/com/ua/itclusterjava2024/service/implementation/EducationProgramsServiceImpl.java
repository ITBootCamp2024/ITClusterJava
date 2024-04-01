package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.EducationPrograms;
import com.ua.itclusterjava2024.repository.ProgramsRepository;
import com.ua.itclusterjava2024.service.interfaces.EducationProgramsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationProgramsServiceImpl implements EducationProgramsService {

    private final ProgramsRepository programsRepository;

    public EducationProgramsServiceImpl(ProgramsRepository programsRepository) {
        this.programsRepository = programsRepository;
    }

    @Override
    public EducationPrograms create(EducationPrograms educationPrograms) {
        return programsRepository.save(educationPrograms);
    }

    @Override
    public Optional<EducationPrograms> readById(long id) {
        return programsRepository.findById(id);
    }

    @Override
    public EducationPrograms update(long id, EducationPrograms t) {
        t.setId(id);
        return programsRepository.save(t);
    }

    @Override
    public void delete(long id) {
        programsRepository.deleteById(id);
    }

    @Override
    public Page<EducationPrograms> getAll(Pageable pageable) {
        return programsRepository.findAll(pageable);    }

    @Override
    public List<EducationPrograms> getAll() {
        return programsRepository.findAll();
    }
}
