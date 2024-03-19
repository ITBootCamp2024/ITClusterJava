package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.Programs;
import com.ua.itclusterjava2024.repository.ProgramsRepository;
import com.ua.itclusterjava2024.service.interfaces.ProgramsService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Programs readById(long id) {
        return programsRepository.getOne(id);
    }

    @Override
    public Programs update(Programs t) {
        return programsRepository.save(t);
    }

    @Override
    public void delete(long id) {
        programsRepository.deleteById(id);
    }

    @Override
    public List<Programs> getAll() {
        return programsRepository.findAll();
    }
}
