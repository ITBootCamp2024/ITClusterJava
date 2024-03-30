package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.repository.ProgramsRepository;
import com.ua.itclusterjava2024.service.interfaces.ProgramsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramsServiceImpl implements ProgramsService {

    private final ProgramsRepository programsRepository;

    public ProgramsServiceImpl(ProgramsRepository programsRepository) {
        this.programsRepository = programsRepository;
    }

    @Override
    public Programs create(Programs programs) {
        return programsRepository.save(programs);
    }

    @Override
    public Optional<Programs> readById(long id) {
        return programsRepository.findById(id);
    }

    @Override
    public Programs update(long id, Programs t) {
        t.setId(id);
        return programsRepository.save(t);
    }

    @Override
    public void delete(long id) {
        programsRepository.deleteById(id);
    }

    @Override
    public Page<Programs> getAll(Pageable pageable) {
        return programsRepository.findAll(pageable);    }

    @Override
    public List<Programs> getAll() {
        return programsRepository.findAll();
    }
}
